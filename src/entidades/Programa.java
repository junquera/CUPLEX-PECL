package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by junquera on 8/06/17.
 */

interface Statement {

    public void execute(SymbolTable st);
}


class IdentifierStatement implements Statement {

    private Identifier i;

    public IdentifierStatement(String id, Expression e) {
        this.i = i;
    }

    public IdentifierStatement(String id, Condition c) {
        this.i = i;
    }

    @Override
    public void execute(SymbolTable st) {

    }
}

class IfStatement implements Statement {

    public IfStatement(Condition c, StatementList ifStmt) {

    }

    public IfStatement(Condition c, StatementList ifStmt, StatementList elseStmt) {

    }

    @Override
    public void execute(SymbolTable st) {

    }
}

class RepeatStatement implements Statement {

    public RepeatStatement(Condition c, StatementList stmtList) {

    }

    @Override
    public void execute(SymbolTable st) {

    }
}

class WriteStatement implements Statement {

    public WriteStatement(Expression e) {

    }

    @Override
    public void execute(SymbolTable st) {

    }
}

class ReadStatement implements Statement {

    public ReadStatement(String i) {

    }

    @Override
    public void execute(SymbolTable st) {

    }
}


interface Condition {

    public boolean getValue(SymbolTable vl);

}

class NotCondition implements Condition {

    public NotCondition(Condition c) {

    }

    @Override
    public boolean getValue(SymbolTable vl) {
        return false;
    }
}

class AndCondition implements Condition {

    public AndCondition(Condition c1, Condition c2) {

    }

    @Override
    public boolean getValue(SymbolTable vl) {
        return false;
    }
}

class OrCondition implements Condition {

    public OrCondition(Condition c1, Condition c2) {

    }

    @Override
    public boolean getValue(SymbolTable vl) {
        return false;
    }
}

class EqualCondition implements Condition {

    public EqualCondition(Expression e1, Expression e2) {

    }

    @Override
    public boolean getValue(SymbolTable vl) {
        return false;
    }
}

class MinorEqualCondition implements Condition {

    public MinorEqualCondition(Expression e1, Expression e2) {

    }

    @Override
    public boolean getValue(SymbolTable vl) {
        return false;
    }
}

class MinorCondition implements Condition {

    public MinorCondition(Expression e1, Expression e2) {

    }

    @Override
    public boolean getValue(SymbolTable vl) {
        return false;
    }
}

class MajorCondition implements Condition {

    public MajorCondition(Expression e1, Expression e2) {

    }

    @Override
    public boolean getValue(SymbolTable vl) {
        return false;
    }
}

class MajorEqualCondition implements Condition {

    public MajorEqualCondition(Expression e1, Expression e2) {

    }

    @Override
    public boolean getValue(SymbolTable vl) {
        return false;
    }
}

class BooleanCondition implements Condition {

    public BooleanCondition(boolean b) {

    }

    @Override
    public boolean getValue(SymbolTable vl) {
        return false;
    }
}

interface Expression {
    public int getValue(SymbolTable vl) throws Exception;
}

class SumaExpression implements Expression {
    private Expression e1;
    private Expression e2;

    public SumaExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public int getValue(SymbolTable vl) throws Exception {
        return e1.getValue(vl) + e2.getValue(vl);
    }
}

class RestaExpression implements Expression {
    private Expression e1;
    private Expression e2;

    public RestaExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public int getValue(SymbolTable vl) throws Exception {
        return e1.getValue(vl) - e2.getValue(vl);
    }
}

class MultiplicacionExpression implements Expression {
    private Expression e1;
    private Expression e2;

    public MultiplicacionExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public int getValue(SymbolTable vl) throws Exception {
        return e1.getValue(vl) * e2.getValue(vl);
    }
}

class DivisionExpression implements Expression {
    private Expression e1;
    private Expression e2;

    public DivisionExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public int getValue(SymbolTable vl) throws Exception {
        if (e2.getValue(vl) == 0)
            throw new Exception("Cannot divide by 0.");
        return e1.getValue(vl) / e2.getValue(vl);
    }
}

class IntegerExpression implements Expression {
    private int value;

    public IntegerExpression(int value) {
        this.value = value;
    }

    @Override
    public int getValue(SymbolTable vl) {
        return this.value;
    }
}

class IdentifierExpression implements Expression {

    private String id;

    public IdentifierExpression(String id) {
        this.id = id;
    }

    @Override
    public int getValue(SymbolTable vl) throws Exception {
        Identifier i = vl.get(this.id);
        if (i == null)
            throw new Exception(String.format("%s doesn't exist", this.id));
        else if (!(i instanceof IntegerIdentifier))
            throw new Exception(String.format("Variable %s was defined as an int.", this.id));

        return ((IntegerIdentifier) i).getValue();
    }
}


//-------------------------------------------------------------------------

class Programa {
    SymbolTable symbolTable;
    StatementList stmtList;

    public Programa(SymbolTable symbolTable, StatementList stmtList) {
        this.symbolTable = symbolTable;
        this.stmtList = stmtList;
    }

    public void test() {
        List<Statement> rawStmntList = stmtList.getList();

        for (Statement s : rawStmntList) {
            s.execute(this.symbolTable);
        }
    }
}

class SymbolTable {
    HashMap<String, Identifier> varList;

    public SymbolTable() {
        this.varList = new HashMap<>();
    }

    public void put(String id, Identifier identifier) throws Exception {
        if (this.varList.get(id) != null)
            throw new Exception(String.format("Var %s duplicated.", id));

        this.varList.put(id, identifier);
    }

    public Identifier get(String id) {
        return this.varList.get(id);
    }
}

class StatementList {
    List<Statement> list;

    public StatementList() {
        this.list = new ArrayList<>();
    }

    public void add(Statement stmt) {
        this.list.add(stmt);
    }

    public List<Statement> getList() {
        return this.list;
    }
}

interface Identifier {
}

class PseudoIdentifier implements Identifier {
    public static final int TYPE = -1;

    private String id;
    private String value;

    public PseudoIdentifier(String id) {
        this.id = id;
        this.value = id;
    }
}

class IntegerIdentifier implements Identifier {
    public static final int TYPE = 0;

    private String id;
    private int value;

    public IntegerIdentifier(String id, int value) {
        this.id = id;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

class BooleanIdentifier implements Identifier {
    public static final int TYPE = 1;

    private String id;
    private boolean value;

    public BooleanIdentifier(String id, boolean value) {
        this.id = id;
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }
}


