package com.company.test980;

import java.util.Arrays;

public class Solution {
    private int[][] dirs ={{-1,0},{0,1},{1,0},{0,-1}};
    private int[][] grid;
    private int R,C;
    private int start,end;
    private int[][]memo;
    public int uniquePathsIII(int[][] grid) {
        this.grid=grid;
        R=grid.length;
        C=grid[0].length;
        int left=R*C;
        memo=new int[(1<<(R*C))][R*C];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i],-1);
        }
        for (int i = 0; i <R ; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j]==1){
                    start=i*C+j;
                    grid[i][j]=0;
                }else if (grid[i][j]==2){
                    end=i*C+j;
                    grid[i][j]=0;
                }
                else if (grid[i][j]==-1)
                    left--;
            }
        }
        int visited =0;
        return dfs(visited,start,left);
    }

    private int dfs(int visited,int v, int left) {
        if (memo[visited][v]!=-1)
            return memo[visited][v];
        visited+=(1<<v);
        left--;
        if (left==0&&v==end){
            visited-=(1<<v);
            memo[visited][v]=1;
            return 1;
        }
        int x=v/C,y=v%C;
        int res=0;
        for (int d = 0; d < 4; d++) {
            int nextX=x+dirs[d][0],nextY=y+dirs[d][1];
            int next=nextX*C+nextY;
            if (inArea(nextX,nextY)&&grid[nextX][nextY]==0&&(visited&1<<next)==0)
                res+=dfs(visited,next,left);
        }
        visited-=(1<<v);
        memo[visited][v]=1;
        return res;
    }

    private boolean inArea(int x, int y) {
        return x>=0&&x<R&&y>=0&&y<C;
    }
}
