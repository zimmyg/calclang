package com.zim.calc.inmemory.example;

import com.zim.calc.context.CalcFieldContext;
import com.zim.calc.context.CalcFieldInputField;
import com.zim.calc.context.CalculationFunction;
import com.zim.calc.context.FieldDataType;
import com.zim.calc.expression.Expression;
import com.zim.calc.inmemory.ExpressionBuilder;
import com.zim.calc.inmemory.InMemoryContext;
import com.zim.calc.inmemory.InMemoryCalcFieldProcessor;
import com.zim.calc.inmemory.function.*;
import com.zim.dataset.Dataset;
import com.zim.dataset.DatasetFactory;

import java.sql.Timestamp;
import java.util.*;

public class Main {

    public static final List<Object[]> testDataSet = new LinkedList<>();
    public static final List<FieldDataType> testDataSetColTypes = new LinkedList<>();

    static {
        testDataSetColTypes.add(FieldDataType.STRING);
        testDataSetColTypes.add(FieldDataType.NUMERIC);
        testDataSetColTypes.add(FieldDataType.BOOLEAN);
        testDataSetColTypes.add(FieldDataType.DATE);
        testDataSetColTypes.add(FieldDataType.TIMESTAMP);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            Object[] dataRow = new Object[testDataSetColTypes.size()];

            // Str data
            sb.setLength(0); // clear buf
            sb.append("STRINGDATA_").append(i);
            String col1Data = sb.toString();
            dataRow[0] = col1Data;

            // Num data
            dataRow[1] = i;

            // bool data
            dataRow[2] = (i % 5 == 0); // every fifth row would be true

            // date data
            dataRow[3] = new Date(i);

            // timestamp data
            dataRow[4] = new Timestamp(i);

            testDataSet.add(dataRow);
        }
    }

    public static final List<CalculationFunction> testFunctions = new LinkedList<>();
    public static final List<CalcFieldInputField> testFields = new LinkedList<>();
    public static Map<String, Integer> testFieldIndices = new HashMap<>();

    static {
        CalculationFunction powFunc = new PowFunction();
        testFunctions.add(powFunc);

        CalculationFunction floowFunc = new FloorFunction();
        testFunctions.add(floowFunc);

        CalculationFunction ceilFunc = new CeilFunction();
        testFunctions.add(ceilFunc);

        CalculationFunction makeDateFunc = new MakeDateFunction();
        testFunctions.add(makeDateFunc);

        CalculationFunction dateCmpFunc = new DateCompareFunction();
        testFunctions.add(dateCmpFunc);


        CalcFieldInputField f1 = new CalcFieldInputField(FieldDataType.STRING, "FIELD_1", "String Field");
        testFields.add(f1);
        testFieldIndices.put(f1.getCalculationIdentifier(), 0);

        CalcFieldInputField f2 = new CalcFieldInputField(FieldDataType.NUMERIC, "FIELD_2", "Numeric Field");
        testFields.add(f2);
        testFieldIndices.put(f2.getCalculationIdentifier(), 1);

        CalcFieldInputField f3 = new CalcFieldInputField(FieldDataType.BOOLEAN, "FIELD_3", "Boolean Field");
        testFields.add(f3);
        testFieldIndices.put(f3.getCalculationIdentifier(), 2);

        CalcFieldInputField f4 = new CalcFieldInputField(FieldDataType.DATE, "FIELD_4", "Date Field");
        testFields.add(f4);
        testFieldIndices.put(f4.getCalculationIdentifier(), 3);
    }

    // TODO: Implement multiple field evaluation with new dataset abstractions, make sure it works for dependent fields.
    // TODO: This will probably require a new processor class which handles multiple calc fields.
    // TODO: This is mostly to simplify the concerns of the basic version, since it never needs to know about any of the
    // TODO: higher-level stuff for evaluating multiple fields.

    // TODO: Better error messages from CalcFieldExpressionBuilder, line numbers, etc. This should be pretty easy.

    // TODO: Make minimumDecimalRoundingScale in InMemoryCalcFieldProcessor configurable.

    // TODO: Potentially function overloading?

    // TODO: Better robustness for datasets/iterators (error checking/handling), especially concerning very large datasets

    // TODO: This one doesn't work because you can't concat boolean and string, should we allow this?
    // TODO: The answer depends on the database support for doing this. If we don't want this to work, but want to
    // TODO: support it for in-memory operations, we could introduce a toString-like function which converts for you.
    // TODO: This would require function overloading though...
    // "CMP is: " + DATECMP(FIELD_4, FIELD_4) + ", EQ is: " + (FIELD_4 = FIELD_4)

    // NOTE: Test calculations:
    // CASE WHEN FIELD_2 % 3 = 0 THEN DATECMP(DATE("2012-10-1", "yyyy-MM-dd"), DATE("2012-10-1", "yyyy-MM-dd")) WHEN FIELD_2 % 3 = 1 THEN DATECMP(DATE("2012-10-1", "yyyy-MM-dd"), DATE("2012-10-12", "yyyy-MM-dd")) ELSE DATECMP(DATE("2012-10-12", "yyyy-MM-dd"), DATE("2012-10-1", "yyyy-MM-dd")) END
    // DATECMP(DATE("2012-10-1", "yyyy-MM-dd"), DATE("2012-10-1", "yyyy-MM-dd"))
    // DATECMP(DATE("2012-10-1", "yyyy-MM-dd"), DATE("2012-10-12", "yyyy-MM-dd"))
    // DATECMP(DATE("2012-10-12", "yyyy-MM-dd"), DATE("2012-10-1", "yyyy-MM-dd"))

    // FizzBuzz!
    // CASE WHEN FIELD_2 % 15 = 0 THEN "FIZZBUZZ" WHEN FIELD_2 % 5 = 0 THEN "BUZZ" WHEN FIELD_2 % 3 = 0 THEN "FIZZ" ELSE FIELD_2 + "" END

    public static void main(String[] args) throws Exception {
        DatasetFactory setFactory = new DatasetFactory();

        Dataset inputDataset = setFactory.fromRowOrderedData_CollecOfArrays(testDataSet);

        System.out.println("Enter test expression:");

        Scanner input = new Scanner(System.in);
        StringBuilder inputLine = new StringBuilder();
        String currLine = null;
        while (!(currLine = input.nextLine()).equals("")) {
            inputLine.append(currLine).append("\n");
        }
        input.close();


        CalcFieldContext ctx = new InMemoryContext(testFunctions, testFields);
        ExpressionBuilder builder = new ExpressionBuilder(ctx);

        long parseStart = System.currentTimeMillis();

        Expression parsedExpression = builder.parseExpressionFromString(inputLine.toString());
        if (parsedExpression == null) {
            throw new Exception("Something went horribly wrong, there was no result expression, but there was no exception either.");
        }

        long parseEnd = System.currentTimeMillis();
        System.out.println("Parsing took: " + (parseEnd-parseStart) + "ms.");

        FieldDataType outputType = parsedExpression.getResultType();
        System.out.println("Result type is: " + outputType.toString());

        long evalStart = System.currentTimeMillis();

        InMemoryCalcFieldProcessor calcFieldProcessor = new InMemoryCalcFieldProcessor(inputDataset, testFieldIndices);
        List<Object> result = calcFieldProcessor.calculateField(parsedExpression);

        long evalEnd = System.currentTimeMillis();
        System.out.println("Evaluation took: " + (evalEnd-evalStart) + "ms for " + testDataSet.size() + " rows.");

        List<Collection<Object>> outputRawDataset = new LinkedList<>();
        outputRawDataset.add(result);

        // NOTE: REMEMBER THAT THE RESULT IS COLUMN-ORDERED
        Dataset calcFieldDataset = setFactory.fromColOrderedData_CollecOfCollecs(outputRawDataset);
        Dataset finalDataset = setFactory.compose(inputDataset, calcFieldDataset);

        final int MAX_PRINT_ROWS = 10;
        int actualPrintRows = Math.min(MAX_PRINT_ROWS, result.size());

        Iterator<Object[]> rowIt = finalDataset.iterator();
        for (int i = 0; i < actualPrintRows; i++) {
            Object[] row = rowIt.next();
            boolean first = true;
            for (Object colVal: row) {
                if (!first) {
                    System.out.print(", ");
                }
                System.out.print(colVal);
                first = false;
            }
            System.out.println();
        }
        System.out.flush(); // make sure the output buffer is flushed before we quit, because there's annoying overlapping log messages when we exit
    }
}
