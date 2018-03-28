package com.zim.calc.inmemory;

import com.zim.calc.context.CalcFieldInputField;
import com.zim.calc.context.FieldDataType;
import com.zim.calc.expression.*;
import com.zim.calc.inmemory.function.InMemoryCalcFunction;
import com.zim.dataset.Dataset;
import com.zim.dataset.DatasetFactory;

import java.math.BigDecimal;
import java.util.*;

public class InMemoryCalcFieldProcessor {

    /**
     * When dividing numbers, this defines the minimum scale we will round the result to. If the scales of the numbers
     * are larger, we will round to the largest one.
     */
    // TODO: Make this configurable
    public static final int minimumDecimalRoundingScale = 10;

    private Dataset dataset;
    private Map<String, Integer> fieldIndexMap; // index into the dataset for each field's data, mapped by the field's calc id

    // dependencies
    private DatasetFactory dsf;

    public InMemoryCalcFieldProcessor(Dataset dataset, Map<String, Integer> fieldIndexMap) {
        this.dataset = dataset;
        this.fieldIndexMap = fieldIndexMap;
    }

    private DatasetFactory getDatasetFactory() {
        if (dsf == null) dsf = new DatasetFactory();
        return dsf;
    }

    public void setDatasetFactory(DatasetFactory dsf) {
        this.dsf = dsf;
    }

    public List<Object> calculateField(Expression calcExpression) throws Exception {
        return evaluateExpression(calcExpression, dataset);
    }

    private List<Object> evaluateExpression(Expression expr, Dataset exprDataset)
            throws Exception {
        List<Object> result = null;

        if (expr instanceof LiteralExpression) {
            result = evaluateLiteralExpression((LiteralExpression) expr, exprDataset);
        } else if (expr instanceof UnaryOpExpression) {
            result = evaluateUnaryExpression((UnaryOpExpression) expr, exprDataset);
        } else if (expr instanceof BinaryOpExpression) {
            result = evaluateBinaryExpression((BinaryOpExpression) expr, exprDataset);
        } else if (expr instanceof ListExpression) {
            result = evaluateListExpression((ListExpression) expr, exprDataset);
        } else if (expr instanceof FunctionExpression) {
            result = evaluateFunctionExpression((FunctionExpression) expr, exprDataset);
        } else if (expr instanceof CaseExpression) {
            result = evaluateCaseExpression((CaseExpression) expr, exprDataset);
        }

        return result;
    }

    private List<Object> fillValueToSize(Object value, int size) {
        List<Object> result = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            result.add(value);
        }

        return result;
    }

    private BigDecimal getBD(Object o) {
        BigDecimal result = null;

        if (o instanceof BigDecimal) {
            result = (BigDecimal) o;
        } else if (o != null) {
            result = new BigDecimal(o.toString());
        }

        return result;
    }

    private Boolean getBool(Object o) {
        Boolean result = null;

        if (o instanceof Boolean) {
            result = (Boolean) o;
        } else if (o != null) {
            result = Boolean.parseBoolean(o.toString());
        }

        return result;
    }

    private String getString(Object o) {
        String result = null;

        if (o instanceof String) {
            result = (String) o;
        } else if (o != null) {
            result = o.toString();
        }

        return result;
    }

    // this is annoying, even though I am checking instanceof List, it still complains
    @SuppressWarnings("unchecked")
    private List<Object> getList(Object o) {
        List<Object> result = null;

        if (o instanceof List) {
            result = (List<Object>) o;
        }

        return result;
    }

    private Date getDate(Object o) {
        Date result = null;

        if (o instanceof Date) {
            result = (Date) o;
        }

        return result;
    }

    private List<Object> evaluateLiteralExpression(LiteralExpression expr, Dataset exprDataset) throws Exception {
        List<Object> result;

        Object val = expr.getLiteralValue();
        if (val instanceof CalcFieldInputField) {
            // field literal, return field data
            CalcFieldInputField field = (CalcFieldInputField) val;
            Integer fieldIndex = fieldIndexMap.get(field.getCalculationIdentifier());
            if (fieldIndex == null || fieldIndex < 0 || fieldIndex >= fieldIndexMap.size()) {
                throw new Exception("Field literal value not found!");
            }

            result = new LinkedList<>();
            Iterator<Object[]> datasetIt = exprDataset.iterator();
            while (datasetIt.hasNext()) {
                Object[] row = datasetIt.next();
                result.add(row[fieldIndex]);
            }
        } else {
            // real literal, return constant data packed out to the dataset width
            result = fillValueToSize(val, exprDataset.getRowCount());
        }

        return result;
    }

    private List<Object> evaluateUnaryExpression(UnaryOpExpression expr, Dataset exprDataset) throws Exception {
        List<Object> result = new LinkedList<>();
        FieldDataType type = expr.getResultType();

        List<Object> operandDataset = evaluateExpression(expr.getOperand(), exprDataset);
        Iterator<Object> opIt = operandDataset.iterator();
        while (opIt.hasNext()) {
            Object operand = opIt.next();
            opIt.remove();

            if (type == FieldDataType.NUMERIC) {
                BigDecimal bd = getBD(operand);
                if (bd != null) {
                    switch (expr.getOperator()) {
                        case ADD: {
                            // NOTE: This doesn't really make sense, maybe remove it? It's nice for lining up expressions though
                            // bd = bd;
                        }
                        break;

                        case SUB: {
                            bd = bd.multiply(new BigDecimal("-1")); // flip sign
                        }
                        break;

                        default:
                            // invalid operator for type
                            throw new Exception("Invalid operator for Numeric type unary expression!");
                    }
                }
                result.add(bd);

            } else if (type == FieldDataType.BOOLEAN) {
                Boolean bool = getBool(operand);
                if (bool != null) {
                    bool = !bool;
                }

                result.add(bool);
            }
        }

        return result;
    }

    private Boolean getEqualityResult(BinaryOpExpression.BinaryOperator operator, Object lhs, Object rhs) {
        Boolean outputValue = null;

        if (lhs == null && rhs == null) {
            if (operator == BinaryOpExpression.BinaryOperator.EQUAL) outputValue = true;
            if (operator == BinaryOpExpression.BinaryOperator.NOTEQUAL) outputValue = false;
        } else if (lhs == null || rhs == null) {
            if (operator == BinaryOpExpression.BinaryOperator.EQUAL) outputValue = false;
            if (operator == BinaryOpExpression.BinaryOperator.NOTEQUAL) outputValue = true;
        } else {
            if (operator == BinaryOpExpression.BinaryOperator.EQUAL) outputValue = lhs.equals(rhs);
            if (operator == BinaryOpExpression.BinaryOperator.NOTEQUAL) outputValue = !lhs.equals(rhs);
        }

        return outputValue;
    }

    private List<Object> evaluateBinaryExpression(BinaryOpExpression expr, Dataset exprDataset) throws Exception {
        List<Object> result = new LinkedList<>();
        FieldDataType resultType = expr.getResultType();

        Expression lhsExpr = expr.getLhs();
        Expression rhsExpr = expr.getRhs();

        List<Object> lhsData = evaluateExpression(lhsExpr, exprDataset);
        List<Object> rhsData = evaluateExpression(rhsExpr, exprDataset);

        FieldDataType lhsType = lhsExpr.getResultType();
        FieldDataType rhsType = rhsExpr.getResultType();

        Iterator<Object> lhsIt = lhsData.iterator();
        Iterator<Object> rhsIt = rhsData.iterator();

        BinaryOpExpression.BinaryOperator operator = expr.getOperator();

        while (lhsIt.hasNext()) {
            Object lhs = lhsIt.next();
            Object rhs = rhsIt.next();

            lhsIt.remove();
            rhsIt.remove();

            Object outputValue = null;

            if (resultType == FieldDataType.STRING) {
                String lhsStr = getString(lhs);
                String rhsStr = getString(rhs);

                switch (operator) {
                    case ADD: {
                        if (lhs == null && rhs == null) {
                            outputValue = null; // not necessary, but being verbose
                        } else if (lhs == null) {
                            outputValue = rhs;
                        } else if (rhs == null) {
                            outputValue = lhs;
                        } else {
                            outputValue = lhsStr + rhsStr;
                        }
                    }
                    break;

                    default:
                        throw new Exception("Invalid operator for String binary operation!");
                }

            } else if (resultType == FieldDataType.BOOLEAN) {

                switch (operator) {
                    case AND:
                    case OR: {
                        Boolean lhsBool = getBool(lhs);
                        Boolean rhsBool = getBool(rhs);

                        if (lhsBool == null || rhsBool == null) {
                            outputValue = null;
                        } else {
                            if (operator == BinaryOpExpression.BinaryOperator.AND) outputValue = lhsBool && rhsBool;
                            if (operator == BinaryOpExpression.BinaryOperator.OR) outputValue = lhsBool || rhsBool;
                        }
                    }
                    break;

                    case EQUAL:
                    case NOTEQUAL: {
                        // we don't actually need to handle the case where they're all different data types, because that
                        // should have been validated when we were parsing the calc. They should be the same types.
                        if (lhsType == FieldDataType.STRING) {
                            String lhsStr = getString(lhs);
                            String rhsStr = getString(rhs);
                            outputValue = getEqualityResult(operator, lhsStr, rhsStr);

                        } else if (lhsType == FieldDataType.BOOLEAN) {
                            Boolean lhsBool = getBool(lhs);
                            Boolean rhsBool = getBool(rhs);
                            outputValue = getEqualityResult(operator, lhsBool, rhsBool);

                        } else if (lhsType == FieldDataType.NUMERIC) {
                            BigDecimal lhsBD = getBD(lhs);
                            BigDecimal rhsBD = getBD(rhs);
                            outputValue = getEqualityResult(operator, lhsBD, rhsBD);

                        } else if (lhsType == FieldDataType.DATE) {
                            Date lhsDate = getDate(lhs);
                            Date rhsDate = getDate(rhs);
                            outputValue = getEqualityResult(operator, lhsDate, rhsDate);

                        }
                    }
                    break;

                    case INLIST:
                    case NOTINLIST: {
                        // we don't actually need to handle the case where they're all different data types, because that
                        // should have been validated when we were parsing the calc. They should be the same types.

                        // reuse the equality test method to tell us whether this value is in the list
                        BinaryOpExpression.BinaryOperator equalityTestOperator = BinaryOpExpression.BinaryOperator
                                                                                         .EQUAL;
                        List<Object> rhsList = getList(rhs);

                        // the args passed for the equality test after they have been 'sanitised' into the correct data type
                        Object lhsEvaluated = null;
                        List<Object> rhsEvaluated = new LinkedList<>();

                        // properly evaluate values into real or null, based on whether they're valid for that data type or not
                        if (lhsType == FieldDataType.STRING) {
                            lhsEvaluated = getString(lhs);

                            for (Object rhsElem : rhsList) {
                                String rhsElemStr = getString(rhsElem);
                                rhsEvaluated.add(rhsElemStr);
                            }

                        } else if (lhsType == FieldDataType.BOOLEAN) {
                            lhsEvaluated = getBool(lhs);

                            for (Object rhsElem : rhsList) {
                                Boolean rhsElemBool = getBool(rhsElem);
                                rhsEvaluated.add(rhsElemBool);
                            }

                        } else if (lhsType == FieldDataType.NUMERIC) {
                            lhsEvaluated = getBD(lhs);

                            for (Object rhsElem : rhsList) {
                                BigDecimal rhsElemBD = getBD(rhsElem);
                                rhsEvaluated.add(rhsElemBD);
                            }

                        } else if (lhsType == FieldDataType.DATE) {
                            lhsEvaluated = getDate(lhs);

                            for (Object rhsElem : rhsList) {
                                Date rhsElemDate = getDate(rhsElem);
                                rhsEvaluated.add(rhsElemDate);
                            }
                        }

                        // now check if it is in/not in list
                        boolean inList = false;
                        for (Object rhsElem : rhsEvaluated) {
                            inList = getEqualityResult(equalityTestOperator, lhsEvaluated, rhsElem);

                            if (inList) break;
                        }

                        if (operator == BinaryOpExpression.BinaryOperator.INLIST) outputValue = inList;
                        if (operator == BinaryOpExpression.BinaryOperator.NOTINLIST) outputValue = !inList;

                    }
                    break;

                    case GT:
                    case LT:
                    case GE:
                    case LE: {
                        BigDecimal lhsBD = getBD(lhs);
                        BigDecimal rhsBD = getBD(rhs);

                        if (lhsBD == null || rhsBD == null) {
                            outputValue = false;
                        } else {
                            if (operator == BinaryOpExpression.BinaryOperator.GT)
                                outputValue = (lhsBD.compareTo(rhsBD) > 0);
                            if (operator == BinaryOpExpression.BinaryOperator.LT)
                                outputValue = (lhsBD.compareTo(rhsBD) < 0);
                            if (operator == BinaryOpExpression.BinaryOperator.GE)
                                outputValue = (lhsBD.compareTo(rhsBD) >= 0);
                            if (operator == BinaryOpExpression.BinaryOperator.LE)
                                outputValue = (lhsBD.compareTo(rhsBD) <= 0);
                        }
                    }
                    break;

                    default:
                        throw new Exception("Invalid operator for Boolean binary operation!");
                }

            } else if (resultType == FieldDataType.NUMERIC) {
                BigDecimal lhsBD = getBD(lhs);
                BigDecimal rhsBD = getBD(rhs);

                switch (operator) {
                    case ADD:
                    case SUB: {
                        if (lhsBD == null && rhsBD == null) {
                            outputValue = new BigDecimal("0");
                        } else if (lhsBD == null) {
                            outputValue = rhsBD;
                        } else if (rhsBD == null) {
                            outputValue = lhsBD;
                        } else {
                            if (operator == BinaryOpExpression.BinaryOperator.ADD) outputValue = lhsBD.add(rhsBD);
                            if (operator == BinaryOpExpression.BinaryOperator.SUB) outputValue = lhsBD.subtract(rhsBD);
                        }
                    }
                    break;

                    case MUL:
                    case DIV:
                    case MOD: {
                        if (lhsBD == null || rhsBD == null) {
                            outputValue = new BigDecimal("0");
                        } else {
                            if (operator == BinaryOpExpression.BinaryOperator.MUL) outputValue = lhsBD.multiply(rhsBD);
                            if (operator == BinaryOpExpression.BinaryOperator.MOD) outputValue = lhsBD.remainder(rhsBD).abs();
                            if (operator == BinaryOpExpression.BinaryOperator.DIV) {
                                // use the largest scale available when dividing, applying a minimum scale
                                int maxScale = Math.max(Math.max(lhsBD.scale(), rhsBD.scale()), minimumDecimalRoundingScale);
                                outputValue = lhsBD.divide(rhsBD, maxScale, BigDecimal.ROUND_HALF_EVEN);
                            }
                        }

                    }
                    break;

                    default:
                        throw new Exception("Invalid operator for Numeric binary operation!");
                }

            }

            result.add(outputValue);
        }

        return result;
    }

    private boolean isConstantLiteralExpression(Expression e) {
        if (e instanceof LiteralExpression) {
            LiteralExpression le = (LiteralExpression)e;
            return !(le.getLiteralValue() instanceof CalcFieldInputField);
        }

        return false;
    }

    // It's really just returning a one-length list which contains a list with all of the unique in-list values for this expression
    private List<Object> evaluateListExpression(ListExpression expr, Dataset exprDataset) throws Exception {
        List<Object> result = new LinkedList<>();
        for (Expression listItem : expr.getListExpressions()) {
            List<Object> listValues;
            if (isConstantLiteralExpression(listItem)) {
                // this is a weird special case, because we don't want the expression to fill out the entire dataset
                // size with constant data, we just want it once
                List<Object[]> singleRow = Collections.singletonList(exprDataset.iterator().next());
                Dataset subDataset = getDatasetFactory().fromRowOrderedData_CollecOfArrays(singleRow);

                listValues = evaluateLiteralExpression((LiteralExpression) listItem, subDataset);
            } else {
                listValues = evaluateExpression(listItem, exprDataset);
            }

            // deduplicate the results
            if (listValues != null) {
                for (Object o : listValues) {
                    if (!result.contains(o)) {
                        result.add(o);
                    }
                }
            }
        }

        List<Object> resultList = new LinkedList<>();
        resultList.add(result);
        return resultList;
    }

    private List<Object> evaluateFunctionExpression(FunctionExpression expr, Dataset exprDataset) throws Exception {
        List<List<Object>> argumentData = new LinkedList<>();

        if (!(expr.getFunction() instanceof InMemoryCalcFunction))
            throw new Exception("Error, invalid function!");

        InMemoryCalcFunction func = (InMemoryCalcFunction) expr.getFunction();
        if (expr.getArgumentExpressions() != null && !expr.getArgumentExpressions().isEmpty()) {
            for (Expression argExpr : expr.getArgumentExpressions()) {
                argumentData.add(evaluateExpression(argExpr, exprDataset));
            }
        }

        return func.evaluate(argumentData);
    }

    private boolean compPassesForRow(Expression comp, Object[] row) throws Exception {
        boolean result = false;

        // create dataset with one row which we can pass down
        Dataset subDataset = getDatasetFactory().fromRowOrderedData_CollecOfArrays(Collections.singletonList(row));

        List<Object> resultValues = evaluateExpression(comp, subDataset); // run the comparison expression on a single row
        if (resultValues != null && !resultValues.isEmpty()) {
            result = (Boolean) resultValues.get(0); // comparison expressions should always return boolean, so grab the first result data (though it should only be one item long anyway)
        }

        return result;
    }

    private Dataset getTempDatasetWithColCount(int colCount) {
        return getDatasetFactory().emptyExpandableDatasetWithCols(colCount);
    }

    private Dataset[] collectBatchDataByCaseExecution(CaseExpression expr, Dataset exprDataset, LinkedList<Integer> expressionBranchRowEvaluationOrder) throws Exception {
        int whenCount = expr.getWhenExpressions().size();
        int expressionBranchCount =  whenCount + (expr.getElseExpression() == null ? 0 : 1);

        // initialize datasets which we will be bulk-evaluating
        Dataset[] expressionBranchDatasets = new Dataset[expressionBranchCount];
        int inputColCount = exprDataset.getColCount();
        for (int i = 0; i < expressionBranchCount; i++) {
            expressionBranchDatasets[i] = getTempDatasetWithColCount(inputColCount);
        }

        // TODO: Started trying to do bulk processing of the comparison expressions as well, using a filter-style
        // TODO: algo which evaluates the comp for all rows at once, then takes the passing ones out and puts them in
        // TODO: for the then expression that it passed for, continuing to evaluate the remaining rows for each successive
        // TODO: comparison. This seems like a great idea and would probably give us better performance, but I can't
        // TODO: seem to work out a good way to keep the input order when doing this. Doing it this way means that the
        // TODO: rows get all jumbled when they are output, which is bad. Think of a way to track the orders of input
        // TODO: rows versus the orders that they appear in each sub-expression, inclusing rows which did not pass for any expressions.
//        // add rows to each expression branch so that we can bulk-execute them
//        int expressionIdx = 0;
//        Dataset scratchDataset = exprDataset;// we will use this to store the 'remainder' data which is going into each comparison expression, it should start as the entire dataset
//        for (Expression compExpr: expr.getWhenExpressions()) {
//            if (scratchDataset == null || scratchDataset.getRowCount() == 0) {
//                break;
//            }
//
//            Iterator<Object[]> dataIt = scratchDataset.iterator();
//            scratchDataset = getTempDatasetWithColCount(inputColCount); // this will be used for the next expression's input
//            List<Object> expressionResult = evaluateExpression(compExpr, scratchDataset);
//            for (Object rowPassed: expressionResult) {
//                Object[] inputRow = dataIt.next();
//                Boolean passed = getBool(rowPassed);
//                if (passed == null || !passed) {
//                    // did not pass this row, add it to the temp dataset, so that it can be checked for the next comp
//                    scratchDataset.addRow(inputRow);
//                } else {
//                    // passed for this row, add it to this expression's dataset so that the result can be evaluated
//                    Dataset thenDataset = expressionBranchDatasets[expressionIdx];
//                    thenDataset.addRow(inputRow);
//                }
//            }
//
//            expressionIdx++;
//        }
//
//        // if there is anything left in the scratch dataset, it did not pass any comps, if there is an else expr, put it into that dataset, if not, put nulls into the expression branch index for that row


        Iterator<Object[]> dataIt = exprDataset.iterator();
        while (dataIt.hasNext()) {
            Object[] row = dataIt.next();

            int resultExpressionBranch = -1;
            int whenExprIdx = 0;
            for (Expression whenExpr : expr.getWhenExpressions()) {
                if (compPassesForRow(whenExpr, row)) { // we can be smarter about this
                    resultExpressionBranch = whenExprIdx;
                    break;
                }

                whenExprIdx++;
            }

            if (resultExpressionBranch == -1 && expr.getElseExpression() != null) {
                resultExpressionBranch = expressionBranchCount - 1; // the else expression will always be the last one
            }

            if (resultExpressionBranch >= 0) {
                expressionBranchRowEvaluationOrder.add(resultExpressionBranch);
                expressionBranchDatasets[resultExpressionBranch].addRow(row);
            } else {
                expressionBranchRowEvaluationOrder.add(null); // didn't match anything, should output null for this row
            }
        }

        return expressionBranchDatasets;
    }

    private Iterator[] evaluateExpressionBatches(CaseExpression expr, Dataset[] expressionBranchDatasets) throws Exception {
        int expressionBranchCount =  expr.getWhenExpressions().size() + (expr.getElseExpression() == null ? 0 : 1);

        Iterator[] expressionBranchOutputs = new Iterator[expressionBranchCount]; // this is so that we can access each output iterator easily by it's index
        for (int exprIdx = 0; exprIdx < expressionBranchCount; exprIdx++) {
            Dataset data = expressionBranchDatasets[exprIdx];

            List<Object> subResult = null;
            if (data.getRowCount() > 0) { // don't bother evaluating expressions with empty data
                Expression caseExpression;

                if (exprIdx == expressionBranchCount - 1 && expr.getElseExpression() != null) {
                    // this is the else expression
                    caseExpression = expr.getElseExpression();
                } else {
                    // this is an when/then expression, get it from the then expressions
                    caseExpression = expr.getThenExpressions().get(exprIdx); // this is technically bad because it's not a random access list, but the size will be low enough that it won;t really matter
                }

                subResult = evaluateExpression(caseExpression, data);
            }

            if (subResult != null) {
                expressionBranchOutputs[exprIdx] = subResult.iterator();
            }
        }

        return expressionBranchOutputs;
    }

    private List<Object> evaluateCaseExpression(CaseExpression expr, Dataset exprDataset) throws Exception {
        // This requires different processing than the rest of the expression types, because we need to do branching by row

        // list of ordered branch evaluations, which we can unwind after bulk-evaluating the dataset in order to
        // find the output-order of the batched data
        LinkedList<Integer> expressionBranchRowEvaluationOrder = new LinkedList<>();

        // split datasets into batches for each expression, then execute each batch
        Dataset[] expressionBranchDatasets = collectBatchDataByCaseExecution(expr, exprDataset, expressionBranchRowEvaluationOrder);
        Iterator[] expressionBranchOutputs = evaluateExpressionBatches(expr, expressionBranchDatasets);

        // Now that we have evaluated each expression group, we need to unwind the bundled data back into the correct row order.
        // We do this by taking each row from the expression output that we recorded in input order in expressionBranchRowEvaluationOrder above.
        List<Object> result = new LinkedList<>();
        for (Integer exprDatasetIdx: expressionBranchRowEvaluationOrder) {
            if (exprDatasetIdx == null) {
                result.add(null); // the input value did not match any of the expression groups, thus has a null value
                continue;
            }

            // pull the value from the correct expression output
            Iterator outputIt = expressionBranchOutputs[exprDatasetIdx];
            if (outputIt != null && outputIt.hasNext()) {
                result.add(outputIt.next());
            }
        }

        return result;
    }
}
