package org.example.models;

public class CPU extends BasePlayer implements Defeat{
    String defeatLine;

    public String getDefeatLine() {
        return defeatLine;
    }

    public void setDefeatLine(String defeatLine) {
        this.defeatLine = defeatLine;
    }

    public CPU(String name, String defeatLine){
        super(name);
        setDefeatLine(defeatLine);
    }
    @Override
    public void annouceDefeat(){
        StringBuffer line = new StringBuffer();
        line.append(this.getName());
        line.append(": ");
        line.append(this.getDefeatLine());
        System.out.println(line.toString());
    }
}
