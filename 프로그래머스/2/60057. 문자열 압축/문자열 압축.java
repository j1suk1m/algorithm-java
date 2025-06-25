class Solution {
    public int solution(String s) {
        int minLength = s.length(); // 압축 후 최소 길이 // 원래 길이로 초기화

        // 1부터 s.length() / 2까지 자르기 
        for (int chunkSize = 1; chunkSize <= s.length() / 2; chunkSize++) {
            int compressedSize = compress(s, chunkSize);
            minLength = Math.min(minLength, compressedSize);
        }

        return minLength;
    }

    /**
     * 주어진 문자열을 chunkSize 단위로 압축했을 때의 길이를 반환
     */
    private int compress(String s, int chunkSize) {
        StringBuilder result = new StringBuilder(s.length());

        String prevChunk = s.substring(0, chunkSize); // 이전 청크
        int streakCount = 1; // 같은 청크가 연속된 횟수

        for (int i = chunkSize; i < s.length(); i += chunkSize) {
            String currentChunk = s.substring(i, Math.min(i + chunkSize, s.length()));

            if (currentChunk.equals(prevChunk)) {
                streakCount++; 
            } else {
                // 이전 청크를 압축 결과에 추가
                result.append(streakCount > 1 ? streakCount : "")
                      .append(prevChunk);

                // 다음 청크 초기화
                prevChunk = currentChunk;
                streakCount = 1;
            }
        }

        // 마지막 청크 처리
        result.append(streakCount > 1 ? streakCount : "")
              .append(prevChunk);

        return result.length();
    }
}