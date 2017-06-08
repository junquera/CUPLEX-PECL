package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by junquera on 8/06/17.
 */

interface Statement {
}


class IdentifierStatement implements Statement {

    private Identifier i;

    public IdentifierStatement(String id, Expression e){
        this.i = i;
    }

    public IdentifierStatement(String id, Condition c){
        this.i = i;
    }
}

class IfStatement implements Statement {

    public IfStatement(Condition c, StatementList ifStmt){

    }

    public IfStatement(Condition c, StatementList ifStmt, StatementList elseStmt){

    }

}

class RepeatStatement implements Statement {

    public RepeatStatement(Condition c, StatementList stmtList){

    }

}

class WriteStatement implements Statement {

    public WriteStatement(Expression e){

    }

}

class ReadStatement implements Statement {

    public ReadStatement(String i){

    }

}


interface Condition {

    public boolean getValue();

}

class NotCondition implements Condition {

    public NotCondition(Condition c){

    }

    @Override
    public boolean getValue() {
        return false;
    }
}

class AndCondition implements Condition {

    public AndCondition(Condition c1, Condition c2){

    }

    @Override
    public boolean getValue() {
        return false;
    }
}

class OrCondition implements Condition {

    public OrCondition(Condition c1, Condition c2){

    }

    @Override
    public boolean getValue() {
        return false;
    }
}

class EqualCondition implements Condition {

    public EqualCondition(Expression e1, Expression e2){

    }

    @Override
    public boolean getValue() {
        return false;
    }
}

class MinorEqualCondition implements Condition {

    public MinorEqualCondition(Expression e1, Expression e2){

    }

    @Override
    public boolean getValue() {
        return false;
    }
}

class MinorCondition implements Condition {

    public MinorCondition(Expression e1, Expression e2){

    }

    @Override
    public boolean getValue() {
        return false;
    }
}

class MajorCondition implements Condition {

    public MajorCondition(Expression e1, Expression e2){

    }

    @Override
    public boolean getValue() {
        return false;
    }
}

class MajorEqualCondition implements Condition {

    public MajorEqualCondition(Expression e1, Expression e2){

    }

    @Override
    public boolean getValue() {
        return false;
    }
}

class BooleanCondition implements Condition {

    public BooleanCondition(boolean b){

    }

    @Override
    public boolean getValue() {
        return false;
    }
}

interface Expression {
    public int getValue();
}

class SumaExpression implements Expression {

    public SumaExpression(Expression e1, Expression e2){
    }

    @Override
    public int getValue() {
        return 0;
    }
}

class RestaExpression implements Expression {

    public RestaExpression(Expression e1, Expression e2){
    }

    @Override
    public int getValue() {
        return 0;
    }
}

class MultiplicacionExpression implements Expression {

    public MultiplicacionExpression(Expression e1, Expression e2){
    }

    @Override
    public int getValue() {
        return 0;
    }
}

class DivisionExpression implements Expression {

    public DivisionExpression(Expression e1, Expression e2){
    }

    @Override
    public int getValue() {
        return 0;
    }
}

class IntegerExpression implements Expression {

    public IntegerExpression(int i){
    }

    @Override
    public int getValue() {
        return 0;
    }
}

class IdentifierExpression implements Expression {

    public IdentifierExpression(String id){
    }

    @Override
    public int getValue() {
        return 0;
    }
}


//-------------------------------------------------------------------------

class Programa {
    VarList varList;
    StatementList stmtList;

    public Programa(VarList varList, StatementList stmtList) {
        this.varList = varList;
        this.stmtList = stmtList;
    }
}

class VarList {
    HashMap<String, Identifier> varList;

    public VarList(){
        this.varList = new HashMap<>();
    }

    public void put(String id, Identifier identifier) throws Exception {
        if(this.varList.get(id) != null)
            throw new Exception(String.format("Var %s duplicated.", id));

        this.varList.put(id, identifier);
    }
}

class StatementList {
    List<Statement> list;

    public StatementList() {
        this.list = new ArrayList<>();
    }

    public void add(Statement stmt){
        this.list.add(stmt);
    }
}

interface Identifier {
}


class PseudoIdentifier implements Identifier{
    public static final int TYPE = -1;
    public PseudoIdentifier(String id){}
}

class IntegerIdentifier implements Identifier{
    public static final int TYPE = 0;
    public IntegerIdentifier(String id, int value){}
}

class BooleanIdentifier implements Identifier{
    public static final int TYPE = 1;
    public BooleanIdentifier(String id, boolean value){}
}


