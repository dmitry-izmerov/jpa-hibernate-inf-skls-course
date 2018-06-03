package ru.demi;

import org.hibernate.Session;
import ru.demi.entities.Account;
import ru.demi.entities.Address;
import ru.demi.entities.Bank;
import ru.demi.entities.Budget;
import ru.demi.entities.Credential;
import ru.demi.entities.Currency;
import ru.demi.entities.Market;
import ru.demi.entities.Transaction;
import ru.demi.entities.TransactionType;
import ru.demi.entities.User;
import ru.demi.util.HibernateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Application {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

        Currency euro = new Currency();
        euro.setName("EUR");
        euro.setCountryName("European union");
        euro.setSymbol("Euro sign");
        session.save(euro);

        Market market = new Market();
        market.setMarketName("European market");
        market.setCurrency(euro);
        session.save(market);

        session.getTransaction().commit();

        Market gotMarket = session.get(Market.class, market.getMarketId());
        System.out.println(gotMarket.getCurrency().getName());

        session.close();
	}

    private static User createNewUser(List<Address> addresses) {
        LocalDate birthDate = LocalDate.of(1990, 4, 5);
        User user = new User("Vasya", "Pupkin", birthDate, "vasya@mail.ru", "admin");
        if (Objects.nonNull(addresses)) {
            user.getAddresses().addAll(addresses);
        }

        Credential credential = new Credential();
        credential.setUsername("Vasya Pupkin");
        credential.setPassword("pass");
        credential.setUser(user);

        user.setCredential(credential);
        return user;
    }

    private static List<Address> createAddresses() {
        Address address1 = new Address();
        address1.setAddressLine1("Moroseyka, 11");
        address1.setAddressLine2("Petrovka, 22");
        address1.setCity("Moscow");
        address1.setState("Moscow");
        address1.setZipCode("120204");

        Address address2 = new Address();
        address2.setAddressLine1("line 1");
        address2.setAddressLine2("line 2");
        address2.setCity("Tver");
        address2.setState("Tverskaya oblast");
        address2.setZipCode("160303");

        return Arrays.asList(address1, address2);
    }

    private static Bank createBank(Address address) {
	    Bank bank = new Bank();
        bank.setName("VTB");
        bank.setAddress(address);
        bank.setCreatedBy("Vasya Petrov");
        bank.setCreatedDate(new Date());
        bank.setLastUpdatedBy("Vasya Petrov");
        bank.setLastUpdatedDate(new Date());
        bank.getContacts().put("CLIENT_MANAGER", "Timofei");
        bank.getContacts().put("SELLER", "Alex");
        return bank;
    }


    private static Account createNewAccount() {
        Account account = new Account();
        account.setName("test account");
        account.setInitialBalance(BigDecimal.valueOf(100));
        account.setCurrentBalance(BigDecimal.valueOf(200));
        account.setOpenDate(new Date());
        account.setCloseDate(new Date());
        return account;
    }

    private static List<Transaction> createTransactions(Account account) {
	    List<Transaction> transactions = new ArrayList<>();

        Transaction transaction1 = new Transaction();
        transaction1.setTransactionType(TransactionType.WITHDRAW);
        transaction1.setTitle("transaction 1");
        transaction1.setAmount(BigDecimal.valueOf(10));
        transaction1.setInitialBalance(BigDecimal.valueOf(100));
        transaction1.setClosingBalance(BigDecimal.valueOf(200));
        transaction1.setCreatedBy("Vasya");
        transaction1.setCreatedDate(new Date());
        transaction1.setLastUpdatedBy("Vasya");
        transaction1.setLastUpdatedDate(new Date());
        transaction1.setAccount(account);

        Transaction transaction2 = new Transaction();
        transaction2.setTransactionType(TransactionType.DEPOSIT);
        transaction2.setTitle("transaction 2");
        transaction2.setAmount(BigDecimal.valueOf(100));
        transaction2.setInitialBalance(BigDecimal.valueOf(100));
        transaction2.setClosingBalance(BigDecimal.valueOf(200));
        transaction2.setCreatedBy("Vasya");
        transaction2.setCreatedDate(new Date());
        transaction2.setLastUpdatedBy("Vasya");
        transaction2.setLastUpdatedDate(new Date());
        transaction2.setAccount(account);

        transactions.add(transaction1);
        transactions.add(transaction2);

	    return transactions;
    }

    private static Budget createNewBudget(Account account) {
        Budget budget = new Budget();
        budget.setName("Russian");
        budget.setGoalAmount(new BigDecimal("100.00"));
        budget.setPeriod("monthly");

        budget.getTransactions().addAll(createTransactions(account));

        return budget;
    }


}
