// Problem Link
// https://leetcode.com/problems/trapping-rain-water-ii/description/

// Source Code

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    public int trapRainWater(int[][] heightMap) {
        int res = 0;
        int m = heightMap.length;
        int n = (m == 0) ? 0 : heightMap[0].length;

        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[m][n];


        for(int i = 0;i < m;i++){
            pq.offer(new int[] {i, 0, heightMap[i][0]});
            pq.offer(new int[] {i, n-1, heightMap[i][n-1]});
            visited[i][0] = true;
            visited[i][n-1] = true;
        }


        for(int j = 1;j < n-1;j++){
            pq.offer(new int[] {0, j, heightMap[0][j]});
            pq.offer(new int[] {m-1, j, heightMap[m-1][j]});
            visited[0][j] = true;
            visited[m-1][j] = true;
        }

        while(!pq.isEmpty()){
            int[] cell = pq.poll();

            for(int i = 0;i < 4;i++){
                int x = cell[0] + dx[i];
                int y = cell[1] + dy[i];

                if(x < 0 || x >= m || y < 0 || y >= n || visited[x][y]){
                    continue;
                }


                res += Math.max(0, cell[2] - heightMap[x][y]);
                pq.offer(new int[]{x, y, Math.max(heightMap[x][y],  cell[2])});
                visited[x][y] = true;
            }
        }

        return res;
        
    }
}