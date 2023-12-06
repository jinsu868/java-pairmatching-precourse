package pairmatching.error;

public enum ErrorCode {
    FILE_NOT_EXIST("존재하지 않는 파일 경로입니다."),
    IO_FAIL("버퍼로부터 데이터를 읽어오는데 실패했습니다."),
    INVALID_SELECT_NUMBER("선택 번호는 숫자만 가능합니다."),
    INVALID_RETRIEVE_INFO("유효하지 않은 조회 정보 데이터입니다.");

    private static final String MESSAGE_FORMAT = "[ERROR] %s";

    private String message;

    private ErrorCode(String message) {
        this.message = String.format(MESSAGE_FORMAT, message);
    }

    public String getMessage() {
        return message;
    }

    public IllegalArgumentException getException() {
        return new IllegalArgumentException(getMessage());
    }
}
