package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by junquera on 8/06/17.
 */

interface Statement {

    public void execute(SymbolTable st) throws Exception;
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
    public void execute(SymbolTable st) throws Exception {
        st.set(i.getId(), i);
    }
}

class IfStatement implements Statement {

    private Condition c;
    private StatementList ifStmt;
    private StatementList elseStmt;

    public IfStatement(Condition c, StatementList ifStmt) {
        this.c = c;
        this.ifStmt = ifStmt;
        this.elseStmt = new StatementList();
    }

    public IfStatement(Condition c, StatementList ifStmt, StatementList elseStmt) {
        this.c = c;
        this.ifStmt = ifStmt;
        this.elseStmt = elseStmt;

    }

    @Override
    public void execute(SymbolTable st) throws Exception {
        StatementList evaluated = c.getValue(st) ? ifStmt : elseStmt;

        for (Statement s : evaluated.getList())
            s.execute(st);

    }
}

class RepeatStatement implements Statement {

    private Condition c;
    private StatementList stmtList;

    public RepeatStatement(Condition c, StatementList stmtList) {
        this.c = c;
        this.stmtList = stmtList;
    }

    @Override
    public void execute(SymbolTable st) throws Exception {
        while (c.getValue(st))
            for (Statement s : this.stmtList.getList())
                s.execute(st);
    }
}

class WriteStatement implements Statement {

    private Expression e;

    public WriteStatement(Expression e) {
        this.e = e;
    }

    @Override
    public void execute(SymbolTable st) throws Exception {
        System.out.println(e.getValue(st));
    }
}

class ReadStatement implements Statement {

    private String id;

    public ReadStatement(String id) {
        this.id = id;
    }

    @Override
    public void execute(SymbolTable st) throws Exception {
        String input = new Scanner(System.in).nextLine();
        Identifier i = st.get(this.id);

        if (i == null)
            throw new Exception(String.format("Variable %s doesn't exist", this.id));

        if (i instanceof IntegerIdentifier)
            st.set(this.id, new IntegerIdentifier(this.id, Integer.parseInt(input)));
        else if (i instanceof BooleanIdentifier)
            st.set(this.id, new BooleanIdentifier(this.id, Boolean.parseBoolean(input)));
    }
}


interface Condition {

    public boolean getValue(SymbolTable vl) throws Exception;

}

class NotCondition implements Condition {

    private Condition c;

    public NotCondition(Condition c) {
        this.c = c;
    }

    @Override
    public boolean getValue(SymbolTable vl) throws Exception {
        return !this.c.getValue(vl);
    }
}

class AndCondition implements Condition {

    private Condition c1;
    private Condition c2;

    public AndCondition(Condition c1, Condition c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public boolean getValue(SymbolTable vl) throws Exception {
        return this.c1.getValue(vl) && this.c2.getValue(vl);
    }
}

class OrCondition implements Condition {

    private Condition c1;
    private Condition c2;

    public OrCondition(Condition c1, Condition c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public boolean getValue(SymbolTable vl) throws Exception {
        return this.c1.getValue(vl) || this.c2.getValue(vl);
    }
}

class EqualCondition implements Condition {
    private Expression e1;
    private Expression e2;

    public EqualCondition(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public boolean getValue(SymbolTable vl) throws Exception {
        return this.e1.getValue(vl) == this.e2.getValue(vl);
    }
}

class MinorEqualCondition implements Condition {
    private Expression e1;
    private Expression e2;

    public MinorEqualCondition(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;

    }

    @Override
    public boolean getValue(SymbolTable vl) throws Exception {
        return this.e1.getValue(vl) <= this.e2.getValue(vl);
    }
}

class MinorCondition implements Condition {
    private Expression e1;
    private Expression e2;

    public MinorCondition(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;

    }

    @Override
    public boolean getValue(SymbolTable vl) throws Exception {
        return this.e1.getValue(vl) < this.e2.getValue(vl);
    }
}

class MajorCondition implements Condition {
    private Expression e1;
    private Expression e2;

    public MajorCondition(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;

    }

    @Override
    public boolean getValue(SymbolTable vl) throws Exception {
        return this.e1.getValue(vl) > this.e2.getValue(vl);
    }
}

class MajorEqualCondition implements Condition {
    private Expression e1;
    private Expression e2;

    public MajorEqualCondition(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;

    }

    @Override
    public boolean getValue(SymbolTable vl) throws Exception {
        return this.e1.getValue(vl) >= this.e2.getValue(vl);
    }
}

class BooleanCondition implements Condition {

    private boolean value;

    public BooleanCondition(boolean value) {
        this.value = value;
    }

    @Override
    public boolean getValue(SymbolTable vl) {
        return this.value;
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

    public void test() throws Exception {
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

    public void set(String id, Identifier identifier) throws Exception {
        Identifier i = this.varList.get(id);
        if (i == null)
            throw new Exception(String.format("Variable %s doesn't exist.", id));

        if (identifier.getType() != i.getType())
            throw new Exception(String.format("Type mismatch on %s assign.", id));

        this.varList.replace(id, identifier);
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
    public String getId();

    public int getType();
}

class PseudoIdentifier implements Identifier {
    public static final int TYPE = -1;

    private String id;
    private String value;

    public PseudoIdentifier(String id) {
        this.id = id;
        this.value = id;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getType() {
        return TYPE;
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

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getType() {
        return TYPE;
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

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getType() {
        return TYPE;
    }
}


