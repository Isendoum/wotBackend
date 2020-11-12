package com.wot.wotbackend.testingEnviroment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Gridnode {

    private int value;
    private Gridnode upGridNode;
    private Gridnode downGridNode;
    private Gridnode leftGridNode;
    private Gridnode rightGridNode;

    public Gridnode(int i){
        this.value=i;
        this.setRightGridNode(null);
        this.setLeftGridNode(null);
        this.setDownGridNode(null);
        this.setUpGridNode(null);
    }
    public void addNode(Gridnode gridnode){
        Gridnode gridnode1=this;
        if(gridnode1.getUpGridNode() == null){
            gridnode1.setUpGridNode(gridnode);
            gridnode1.getUpGridNode().setDownGridNode(this);
        }else if(gridnode1.getRightGridNode() == null){
            gridnode1.setRightGridNode(gridnode);
            gridnode1.getRightGridNode().setLeftGridNode(this);
        } else if(gridnode1.getDownGridNode() == null){
            gridnode1.setDownGridNode(gridnode);
            gridnode1.getDownGridNode().setUpGridNode(this);
        } else if(gridnode1.getLeftGridNode() == null){
            gridnode1.setLeftGridNode(gridnode);
            gridnode1.getLeftGridNode().setRightGridNode(this);
        }


    }

    public void printAllNodes(){
        Gridnode gridnode=this;
        while(gridnode!=null){
            System.out.println(gridnode.toString()+"\n");
            if(gridnode.getUpGridNode()!=null){
                gridnode= gridnode.getUpGridNode();
            }

        }
    }
    @Override
    public String toString(){
        String nodeValues="";
        Gridnode gridnode=this;

            if (gridnode.getUpGridNode() == null) {
                nodeValues = "                    UpNode: null \n";
            } else {
                nodeValues = "               UpNode: " + gridnode.getUpGridNode().getValue() + "\n";
            }
            if (gridnode.getLeftGridNode() == null) {
                nodeValues = nodeValues + "LeftNode: null    ";
            } else {
                nodeValues = nodeValues + "LeftNode: " + gridnode.getLeftGridNode().getValue() + "    ";
            }

            nodeValues = nodeValues + "Current Node: " + gridnode.getValue() + "    ";

            if (gridnode.getRightGridNode() == null) {
                nodeValues = nodeValues + "RightNode: null\n";
            } else {
                nodeValues = nodeValues + "RightNode: " + gridnode.getRightGridNode().getValue() + "\n";
            }
            if (gridnode.getDownGridNode() == null) {
                nodeValues = nodeValues + "                   DownNode: null    ";
            } else {
                nodeValues = nodeValues + "               DownNode: " + gridnode.getDownGridNode().getValue() + "\n";
            }





        return nodeValues;
    }

}
