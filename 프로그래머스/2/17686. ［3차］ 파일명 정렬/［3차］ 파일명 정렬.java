import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.*;

class File {
    private final String head;
    private final String number;
    private final String tail;
    private final int order; // 입력된 순서
    
    public File(String fileName, int order) {
        Pattern pattern = Pattern.compile("^([a-zA-Z .-]+?)(\\d{1,5})(.*)$");
        Matcher matcher = pattern.matcher(fileName);

        if (matcher.matches()) {
            this.head = matcher.group(1);
            this.number = matcher.group(2);
            this.tail = matcher.group(3);
            this.order = order;
        } else {
            throw new IllegalArgumentException("잘못된 파일명 형식: " + fileName);
        }
    }
    
    public String getHead() {
        return head.toUpperCase(); // 비교 시 대소문자 구분 없으므로 대문자로 통일해 반환
    }
    
    public int getNumber() {
        return Integer.parseInt(number); // 앞쪽에 등장하는 0을 무시하므로 숫자로 변환해 반환
    }
    
    public int getOrder() {
        return order;
    }
    
    // 전체 파일명 반환
    public String getFileName() {
        StringBuilder sb = new StringBuilder();
        
        return sb.append(head)
                 .append(number)
                 .append(tail)
                 .toString();
    }
}

class Solution {
    
    public String[] solution(String[] files) {
        int fileCount = files.length;
        File[] fileArr = new File[fileCount];
        
        // 파일명과 인덱스로부터 File 객체 생성 후 fileArr에 저장
        for (int i = 0; i < fileCount; i++) {
            String file = files[i];
            fileArr[i] = new File(file, i);
        }
        
        // 비교 기준에 따른 정렬
        Arrays.sort(fileArr, Comparator.comparing(File::getHead) // 대소문자 구분 없이 head 사전 순서로 1차 비교
                                       .thenComparingInt(File::getNumber) // number를 숫자로 변환한 뒤 2차 비교
                                       .thenComparingInt(File::getOrder)); // order를 3차 비교
        
        String[] answer = new String[fileCount];
        
        // fileArr를 순회하며 전체 파일명을 조회해 answer에 저장
        for (int i = 0; i < fileCount; i++) {
            answer[i] = fileArr[i].getFileName();
        }
        
        return answer;
    }
}