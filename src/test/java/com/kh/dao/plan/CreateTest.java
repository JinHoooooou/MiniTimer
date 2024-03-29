package com.kh.dao.plan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.kh.database.DataAccessException;
import com.kh.helper.DaoTestUtils;
import com.kh.helper.DdlHelper;
import com.kh.model.dao.PlanDao;
import com.kh.model.vo.Plan;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateTest {

  private PlanDao planDao;
  private String validUserId1 = "validUserId1";

  @BeforeAll
  public static void setUpAll() {
    DdlHelper.dropTable("DETAIL_PLAN");
  }

  @BeforeEach
  public void setUp() {
    planDao = new PlanDao();
    DdlHelper.dropSequence("PLAN");
    DdlHelper.createSequence("PLAN");
    DdlHelper.dropTable("PLAN");
    DdlHelper.dropTable("USERS");
    DdlHelper.createUsersTable();
    DdlHelper.createPlanTable();

    DaoTestUtils.addUserData(validUserId1);
  }

  @Test
  @DisplayName("save 성공1: valid 데이터가 주어질 때")
  public void saveSuccessTest1() {
    // Given: valid 데이터가 주어진다.
    Plan validPlan = Plan.builder()
        .title("validTitle")
        .writer(validUserId1)
        .startDate(Date.valueOf(LocalDateTime.now().toLocalDate()))
        .endDate((Date.valueOf(LocalDate.now())))
        .remindAlarmDate(Date.valueOf(LocalDate.now()))
        .complete(false)
        .createDate(Date.valueOf(LocalDate.now()))
        .build();

    // When: PlanDao.save() 메서드를 호출한다.
    int result = planDao.save(validPlan);

    // Then: result는 1이다.
    assertThat(result).isEqualTo(1);
    // And: DB의 Plan 테이블에 해당 레코드가 추가된다.
    Plan actual = planDao.findByWriter(validUserId1).get(0);
    assertThat(actual.getWriter()).isEqualTo(validPlan.getWriter());
    assertThat(actual.getTitle()).isEqualTo(validPlan.getTitle());
    assertThat(actual.getStartDate().toString()).isEqualTo(validPlan.getStartDate().toString());
    assertThat(actual.getEndDate().toString()).isEqualTo(validPlan.getEndDate().toString());
    assertThat(actual.getRemindAlarmDate().toString()).isEqualTo(
        validPlan.getRemindAlarmDate().toString());
    assertThat(actual.isComplete()).isEqualTo(validPlan.isComplete());
    assertThat(actual.getCreateDate().toString()).isEqualTo(validPlan.getCreateDate().toString());
  }

  @Test
  @DisplayName("save 성공2: Nullable 컬럼에 대한 데이터가 null일 때")
  public void insertPlanSuccessTest2() {
    // Given: valid 데이터가 주어진다.
    // Nullable 컬럼에 대한 데이터는 null로 주어진다.
    Plan validPlan = Plan.builder()
        .writer(validUserId1)
        .title("validTitle")
        .startDate(null)
        .endDate(null)
        .remindAlarmDate(null)
        .build();

    // When: PlanDao.save() 메서드를 호출한다.
    int result = planDao.save(validPlan);

    // Then: result는 1이다.
    assertThat(result).isEqualTo(1);
    // Then: DB의 Plan 테이블에 해당 레코드가 추가된다.
    Plan actual = planDao.findByWriter(validUserId1).get(0);
    assertThat(actual.getWriter()).isEqualTo(validPlan.getWriter());
    assertThat(actual.getTitle()).isEqualTo(validPlan.getTitle());
    // And: Nullable 컬럼은 null로 저장된다.
    assertThat(actual.getStartDate()).isNull();
    assertThat(actual.getEndDate()).isNull();
    assertThat(actual.getRemindAlarmDate()).isNull();
  }

  @Test
  @DisplayName("save 실패1: invalid writer가 주어질 때")
  public void saveFailTest1() {
    // Given: invalid writer가 주어진다.
    Plan invalidPlan = Plan.builder()
        .writer("invalidUserId")
        .title("validTitle")
        .build();

    // When: PlanDao.save() 메서드를 호출한다.
    // Then: DataAccessException 발생한다.
    assertThatThrownBy(() -> planDao.save(invalidPlan))
        .isInstanceOf(DataAccessException.class)
        .hasMessageContaining("부모 키");
  }

  @Test
  @DisplayName("save 실패2: NotNull 컬럼 데이터가 주어지지 않을 때")
  public void insertPlanFailTest2() {
    // Given: invalid 데이터가 주어진다.
    // NotNull 컬럼인 title이 주어지지 않는다.
    Plan invalidPlan = Plan.builder()
        .writer(validUserId1)
        .build();

    // When: PlanDao.save() 메서드를 호출한다.
    // Then: DataAccessException 발생한다.
    assertThatThrownBy(() -> planDao.save(invalidPlan)).isInstanceOf(DataAccessException.class)
        .hasMessageContaining("NULL");
  }
}
