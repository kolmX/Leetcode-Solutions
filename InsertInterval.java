class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        
        int start = newInterval[0];
        int end = newInterval[1];
        //Find most left and rigth intervals if newInterval intersects with intervals 
        int most_left = intervals.length + 1;
        int most_rigth = -1;
        boolean intersection = false;
        
        for (int i = 0; i < intervals.length; i++){
            boolean start_intersec = start >= intervals[i][0] && start <= intervals[i][1];
            boolean middle_intersec = start <= intervals[i][0] && end >= intervals[i][1];
            boolean end_intersec = end >= intervals[i][0] && end <= intervals[i][1];
            //intersection exists?
            if (start_intersec || middle_intersec || end_intersec){
                intersection = true;
                most_left = (i < most_left) ? i : most_left;
                most_rigth = (i > most_rigth) ? i : most_rigth;
            }
        }

        if (!intersection){
            int [][] result = new int[intervals.length+1][2];
            int i = 0;

            while(i < intervals.length && start > intervals[i][0]){
                result[i][0] = intervals[i][0];
                result[i][1] = intervals[i][1];
                i++;
                
            }
            
            result[i][0] = start;
            result[i][1] = end;
            i++;
            
            while(i < result.length){
                result[i][0] = intervals[i-1][0];
                result[i][1] = intervals[i-1][1];
                i++;
                
            }
            return result;
            
        }
        //Put and merge
        else{
            int intersected = most_rigth - most_left + 1;
            int[][] result = new int[intervals.length - intersected + 1][2];
            
            int left_val = (newInterval[0] < intervals[most_left][0]) ? 
                newInterval[0] : intervals[most_left][0];
            int rigth_val = (newInterval[1] > intervals[most_rigth][1]) ?
                newInterval[1] : intervals[most_rigth][1];
            
            int i = 0;
            
            while (i < most_left){
                result[i][0] = intervals[i][0];
                result[i][1] = intervals[i][1];
                i++;
            }
            result[i][0] = left_val;
            result[i][1] = rigth_val;
            i ++;
            while (i  <  result.length){
                result[i][0] = intervals[i + intersected - 1][0];
                result[i][1] = intervals[i + intersected - 1][1];
                i++;
            }
            return result;
        }
        
    }
}
