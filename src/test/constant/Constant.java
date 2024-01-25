package test.constant;

public interface Constant {
  String LINE = "\r\n";
  String MAIN_TITLE = "======= MINI TIMER APP =======";
  String MAIN_CREATE = "1. Timer 생성";
  String MAIN_START = "2. Timer 시작";
  String MAIN_READ_ALL = "3. 모든 Timer 보기";
  String MAIN_UPDATE = "4. Timer 수정";
  String MAIN_DELETE = "5. Timer 삭제";
  String MAIN_EXIT = "9. 프로그램 종료";
  String MAIN_INPUT_MENU = "메뉴 번호 선택: ";

  String CREATE_TITLE = "======= TIMER 생성 =======";
  String CREATE_INPUT_TITLE = "Title 입력: ";
  String CREATE_INPUT_DEFAULT = "Timer를 입력하시겠습니까? 기본값은 20분입니다. (Y/N): ";
  String CREATE_INPUT_HOUR = "Hour 입력: ";
  String CREATE_INPUT_MINUTE = "Minute 입력: ";
  String CREATE_INPUT_SECOND = "Second 입력: ";
  String CREATE_RESULT_SUCCESS = "새 타이머를 생성하였습니다.";
  String CREATE_INPUT_DEFAULT_ERROR = "잘못 입력하였습니다. 다시 입력해주세요.";

  String READ_ONE_TITLE = "======= TIMER 시작 =======";
  String READ_ALL_TITLE = "======= 모든 Timer 보기 =======";
  String READ_ONE_INPUT_INDEX = "시작할 Timer Id를 입력하세요: (0~%d)";
  String READ_ONE_INPUT_INDEX_ERROR = "Index를 잘못 입력하였습니다. 다시 입력해주세요.";
  String READ_EMPTY = "저장된 Timer가 없습니다.";

  String UPDATE_TITLE = "======= TIMER 수정 =======";
  String UPDATE_EMPTY = "저장된 Timer가 없습니다.";
  String UPDATE_INPUT_INDEX = "수정할 Timer Id를 입력하세요: (0~%d)";
  String UPDATE_INPUT_NEW_TITLE = "수정할 Title 입력: ";
  String UPDATE_INPUT_HOUR = "수정할 Hour 입력: ";
  String UPDATE_INPUT_MINUTE = "수정할 Minute 입력: ";
  String UPDATE_INPUT_SECOND = "수정할 Second 입력: ";
  String UPDATE_RESULT_SUCCESS = "타이머를 수정하였습니다.";

}