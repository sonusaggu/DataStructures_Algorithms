public class Solution {

    private final static SUCCESS = "{status: 200, message: OK}";
    private final static TOOMANY = "{status: 429, message: Too many requests}";
    
    public List<String> rateLimiter(List<String> request){
        List<String> result = new ArrayList<>();

        Map<String, List<Integer>> map = new HashMap<>();
        int time = 0;
        for(String req : request){

            if(!map.containsKey(req)){
                map.put(req, new ArrayList<>());
            }

            List<Integer> list = map.get(req);
            int count5 = 0, count30 = 0;

            for(int val : list){
                int diff = time - val + 1;

                if(diff <= 5){
                    count5++;
                }

                if(diff <= 30){
                    count30++;
                }
            }

            if(count5 >=2 || count30 >=30){
                result.add(TOOMANY);
            }else{
                result.add(SUCCESS);
                list.add(time);
                map.put(req,list);
            }
            time++;
        }

        return result;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        sol.rateLimiter(Arrays.asList("www.domain.com","www.domain1.com"));
    }
}
