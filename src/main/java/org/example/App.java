package org.example;

import org.example.application.services.CategoryService;
import org.example.application.services.GoalService;
import org.example.application.services.TransactionService;
import org.example.domain.repositories.ICategoryRepository;
import org.example.domain.repositories.IGoalRepository;
import org.example.domain.repositories.ITransactionRepository;
import org.example.domain.repositories.IUserRepository;
import org.example.infrastructure.JsonStorage;
import org.example.infrastructure.bootstrap.UserBootstrap;
import org.example.infrastructure.repositories.CategoryRepository;
import org.example.infrastructure.repositories.GoalRepository;
import org.example.infrastructure.repositories.TransactionRepository;
import org.example.infrastructure.repositories.UserRepository;

public final class App {
  private static final App INSTANCE = new App();
  private boolean initialized = false;

  private JsonStorage storage;

  private IUserRepository userRepository;
  private ITransactionRepository transactionRepository;
  private ICategoryRepository categoryRepository;
  private IGoalRepository goalRepository;

  private TransactionService transactionService;
  private CategoryService categoryService;
  private GoalService goalService;

  private App() {}

  public static App getInstance() { return INSTANCE; }

  public synchronized void init() {
    if (initialized) return;

    this.storage = JsonStorage.getInstance();
    this.userRepository = new UserRepository(storage);
    this.transactionRepository = new TransactionRepository(storage);
    this.categoryRepository = new CategoryRepository(storage);
    this.goalRepository = new GoalRepository(storage);

    UserBootstrap
            .getInstance()
            .init(userRepository);

    initialized = true;
  }

  public synchronized TransactionService makeTransactionService() {
    if (!initialized) init();
    if (transactionService == null) {
      this.transactionService = new TransactionService(
              this.transactionRepository,
              this.userRepository,
              this.categoryRepository,
              this.goalRepository
      );
    }
    return this.transactionService;
  }

  public synchronized CategoryService makeCategoryService() {
    if (!initialized) init();
    if (categoryService == null) {
      this.categoryService = new CategoryService(this.categoryRepository);
    }
    return this.categoryService;
  }

  public synchronized GoalService makeGoalService() {
    if (!initialized) init();
    if (goalService == null) {
      this.goalService = new GoalService(this.goalRepository);
    }
    return this.goalService;
  }
}