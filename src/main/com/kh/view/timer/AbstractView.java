package main.com.kh.view.timer;

import main.com.kh.controller.TimerController;

import java.util.Scanner;

public abstract class AbstractView {
  private static final int CREATE = 1;
  private static final int START = 2;
  private static final int READ_ALL = 3;
  private static final int UPDATE = 4;
  private static final int DELETE = 5;
  private static final int EXIT = 9;
  protected TimerController timerController;
  protected Scanner scanner;


  public AbstractView() {
    timerController = new TimerController();
  }

  public static AbstractView subView(int menu, TimerController timerController, Scanner scanner) {
    return switch (menu) {
      case CREATE -> new CreateView(timerController, scanner);
      case START -> new ReadOneView(timerController, scanner);
      case READ_ALL -> new ReadAllView(timerController);
      case UPDATE -> new UpdateView(timerController, scanner);
      case DELETE -> new DeleteView(timerController, scanner);
      case EXIT -> new TimerAppExitView();
      default -> throw new IllegalArgumentException();
    };
  }

  public abstract void execute();
}
