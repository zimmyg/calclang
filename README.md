# calclang
Basic language implementation for calculating fields based on some large dataset, plus in-memory executor for this language.

The calculation expression language only includes features for expression evaluation & data manipulation, such as arithmetic, pre-defined function calls and use of pre-defined variables, and does not provide any data definition features such as defining functions or variables.

The in-memory executor works relatively well, but could probably use some architectural work in order to squeeze more performance out of it.

There are future plans to implement other versions of the executor, such as for in-database execution where the calculation will be performed in-database via SQL returning the entire dataset plus the calculated fields.
This requires quite a bit more framework in terms of SQL generation, so it likely won't ever happen in this project in any meaningful way, but as a long-term goal, I think it's worth putting down.

Other currently planned features include:
 - **Dependent fields execution:** Currently only one field at a time can be calculated and it wouldn't de difficult to allow calculating multiple fields, but that raises the problem of dependent fields. The executor needs to be able to handle dependency resolution of calc fields in order to execute them in dependency-order. Something else to note as a challenge for this item is the subsequent dataset positions of the calc fields.
 - **Function overloading:** Currently there is no support for overloading functions, which makes some things a bit jank. Ideally I would like functions to be written as plugins, so they will need a re-write anyway.
