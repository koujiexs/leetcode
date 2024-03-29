package com.company.test307;

/**
 * @author liyang on 2019/9/22
 */
public class SegmentTree {

    private int[] tree;
    private int[] data;

    public SegmentTree(int[] arr){
        data =new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i]=arr[i];
        }
        tree =new int[4*arr.length];//4n
        buildSegmentTree(0,0,data.length-1);
    }

    //在treeIndex的位置创建表示区间[l..r]的线段树
    private void buildSegmentTree(int treeIndex,int l,int r){
        if(l==r){
            tree[treeIndex]=data[l];
            return;
        }
        int leftTreeIndex=leftChild(treeIndex);
        int rightTreeIndex=rightChild(treeIndex);
        int mid=l+(r-l)/2;
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1,r);
        tree[treeIndex]=tree[leftTreeIndex]+tree[rightTreeIndex];
    }

    public int getSize(){
        return data.length;
    }

    public int get(int index){
        if(index< 0||index>=data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return 2*index+1;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return 2*index+2;
    }

    //返回区间[queryL,queryR]的值
    public int query(int queryL,int queryR){
        if(queryL< 0||queryL>=data.length||queryR< 0||queryR>=data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return query(0,0,data.length-1,queryL,queryR);
    }

    //在以treeId韦根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private int query(int treeIndex,int l,int r,int queryL,int queryR){
        if (l==queryL&&r==queryR){
            return tree[treeIndex];
        }
        int mid=l+(r-l)/2;
        int leftTreeIndex=leftChild(treeIndex);
        int rightTreeIndex=rightChild(treeIndex);
        if (queryL>=mid+1)
            return query(rightTreeIndex,mid+1,r,queryL,queryR);
        else if (queryR<=mid){
            return query(leftTreeIndex,l,mid,queryL,queryR);
        }
        int leftResult=query(leftTreeIndex,l,mid,queryL,mid);
        int rightResult=query(rightTreeIndex,mid+1,r,mid+1,queryR);
        return leftResult+rightResult;
    }
    //将index位置的值，更新为e
    public void set(int index,int e){
        if(index< 0||index>=data.length)
            throw new IllegalArgumentException("Index is illegal.");
        data[index]=e;
        set(0,0,data.length-1,index,e);
    }

    //在以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex,int l ,int r,int index,int e){

        if(l==r){
            tree[treeIndex] =e;
            return;
        }
        int mid=l+(r-l)/2;
        int leftTreeIndex=leftChild(treeIndex);
        int rightTreeIndex=rightChild(treeIndex);
        if (index>=mid+1)
            set(rightTreeIndex,mid+1,r,index,e);
        else
            set(leftTreeIndex,l,mid,index,e);
        tree[treeIndex] =tree[rightTreeIndex]+tree[leftTreeIndex];
    }
}
