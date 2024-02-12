package com.kh.plan.view;

import static com.kh.plan.view.constant.Constant.LINE;
import static com.kh.plan.view.constant.Constant.SELECTED_PLAN;
import static com.kh.plan.view.constant.Constant.UPDATE_INPUT_MEMO;
import static com.kh.plan.view.constant.Constant.UPDATE_INPUT_TITLE;
import static com.kh.plan.view.constant.Constant.UPDATE_PLAN_HEAD;
import static com.kh.plan.view.constant.Constant.UPDATE_RESULT_SUCCESS_FORMAT;

import com.kh.plan.controller.PlanController;
import com.kh.plan.model.vo.Plan;
import java.util.Scanner;

public class UpdatePlanView extends AbstractView {

  private Plan target;
  private String updatedTitle;
  private String updatedMemo;

  public UpdatePlanView(PlanController planController, Plan target) {
    this.subViewCommand = -1;
    this.planController = planController;
    this.target = target;
  }

  @Override
  public void execute() {
    scanner = new Scanner(System.in);
    System.out.println(UPDATE_PLAN_HEAD);
    System.out.printf(SELECTED_PLAN + LINE, target);
    System.out.println();
    inputUpdateTitle();
    inputUpdateMemo();
    planController.update(target, updatedTitle, updatedMemo);
    print();
  }

  private void inputUpdateTitle() {
    System.out.print(UPDATE_INPUT_TITLE);
    updatedTitle = scanner.nextLine();
    System.out.println();
  }

  private void inputUpdateMemo() {
    System.out.print(UPDATE_INPUT_MEMO);
    updatedMemo = scanner.nextLine();
    System.out.println();
  }

  private void print() {
    System.out.printf(UPDATE_RESULT_SUCCESS_FORMAT + LINE, target);
  }
}