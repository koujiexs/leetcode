package com.company.test387;

/**
 * @author liyang on 2019/9/29
 */
public class Solution {
    public int firstUniqChar(String s) {
        int[] freq =new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i)-'a']==1)
                return i;
        }
        return -1;
    }
}
