class Solution {
    
    private class PredSeq{
        public int pred;
        public int seq;
        
        PredSeq(int pred, int seq){
            this.pred = pred;
            this.seq = seq;
        }
    }
    
    public int longestConsecutive(int[] nums) {
        //Map contained key - current number and two elements - seq and prev numbers
        Map<Integer, PredSeq> seq = new HashMap<Integer, PredSeq>();
        
        for (int i = 0; i < nums.length; i++){
            PredSeq ins = new PredSeq(nums[i], nums[i]);
            if (seq.containsKey(nums[i] + 1)){
                PredSeq old = seq.get(nums[i] + 1);
                seq.put(nums[i] + 1, new PredSeq(nums[i], old.seq));
                ins.seq = nums[i] + 1;
                
            }
            
            if (seq.containsKey(nums[i] - 1)){
                PredSeq old = seq.get(nums[i] - 1);
                seq.put(nums[i] - 1, new PredSeq(old.pred, nums[i]));
                ins.pred = nums[i] - 1;
            } 
            seq.put(nums[i], ins);
        }
        
        int longest = 0;
        for (Map.Entry<Integer, PredSeq> elem : seq.entrySet()) {
            int key = elem.getKey();
            int back_key = key;
            int fwd_key = key;
            int val_back = elem.getValue().pred;
            int val_fwd = elem.getValue().seq;
            int length = 1;
            
            while (back_key != val_back){
                length += 1;
                int tmp_key = back_key;
                back_key =val_back;
                val_back = seq.get(back_key).pred;
                //may remove but in java its not convenient (Concurrency error)
                seq.put(tmp_key, new PredSeq(tmp_key, tmp_key));
            }
            
            while (fwd_key != val_fwd){
                length += 1;
                int tmp_key = fwd_key;
                fwd_key = val_fwd;
                val_fwd = seq.get(fwd_key).seq;
                seq.put(tmp_key, new PredSeq(tmp_key, tmp_key));
            }
            
            if (longest < length){
                longest = length;
            }
            
        }
        
        return longest;
    }
}
