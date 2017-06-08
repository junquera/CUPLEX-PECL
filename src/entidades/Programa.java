package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by junquera on 8/06/17.
 */

interface Statement {
}

interface Condition {
}

interface Expression {
}

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


