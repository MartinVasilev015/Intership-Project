package bank.businesslogic;
import java.util.ArrayList;
import java.util.Random;

import bank.entities.Bill;
import bank.entities.Merchant;
import bank.entities.Payment;
import bank.entities.Transaction;

public class BankManager
{	
	Random r = new Random();
	
	public ArrayList<Merchant> getMerchantsData()
	{
		ArrayList<Merchant> merchants = new ArrayList<Merchant>();
		
		merchants.add(new Merchant(
				"1000234",
				"чез електро",
				"cez electro",
				"cez",
				"Абонатен номер",
				true,
				1,
				"Електроразпределителни дружества",
				1,
				true,
				true,
				"електроснабдяване",
				"Чрез ePay.bg могат да се плащат сметки на битови и\r\n"
				+ "стопански клиенти към ЧЕЗ за град София и областите Благоевград, Кюстендил, Перник и\r\n"
				+ "София област, Плевен, Ловеч, Враца, Видин и Монтана.\r\n"
				+ "Изисквана информация в ePay.bg - 12-цифрен клиентски номер. Клиентският номер е\r\n"
				+ "изписан в горната част на всяка фактура, която сте получили от ЧЕЗ в периода след 25 май\r\n"
				+ "2010г.\r\n"
				+ "Срок за плащане чрез ePay.bg - до погасяване на задължението. Срокът за безлихвено\r\n"
				+ "плащане е отбелязан във фактурата от ЧЕЗ за съответния клинетски номер\r\n"
				+ "(ИТН)",
				"^\\d{12}$",
				"Клиентски номер – 12 цифри"
				));
		
		merchants.add(new Merchant(
				"0000123",
				"Софийска вода",
				"Sofiyska voda",
				"Софийска вода",
				"Клиентска сметка",
				true,
				2,
				"ВиК",
				1,
				false,
				true,
				"предоставяне на услуги по водоснабдяване и канализация за гр. София",
				"Чрез ePay.bg могат да се плащат както текущи, така и\r\n"
				+ "стари сметки на частни (битови) и фирмени абонати в гр. София. Изисквана информация в ePay.bg - клиентски номер, посочен във фактурата, като се\r\n"
				+ "въвеждат 7-те цифри до тирето.\r\n"
				+ "Пример, ако номерът във фактурата е 1234567 - 2, в ePay.bg се въвежда 1234567.\r\n"
				+ "Срок за плащане чрез ePay.bg - до издължаване на абоната. Срокът за безлихвено плащане\r\n"
				+ "е отбелязан във фактурата от Софийска вода",
				"^\\d{7}$",
				">Клиентски номер - 7 цифри"
				));
		return merchants;
	}
	
	public Bill checkBill(String merchantId, String subscrNumber) 
	{
		Bill result = new Bill();

		int chance = r.nextInt(100);
		
		setValues(result, chance);

		return result;
	}
	
	public Transaction checkBillBlocked(String transactionId,
			String dateTime, String merchatId, String subscrNumber)
	{
		Transaction result = new Transaction();
		
		int chance = r.nextInt(100);
		
		setValues(result, chance);
		
		return result;
	}

	private void setValues(Payment p, int chance)
	{
		if(chance >= 20) 
		{
			p.setStatus("00");
			p.setShortDesc("OK");
			p.setAmount(1000);
			
			if(p.getClass().getName().equals("Bill"))
			{
				((Bill)p).setValidTo("20103011");
				((Bill)p).setSecondId("1234567890");
				
			}
			else if(p.getClass().getName().equals("Transaction")) 
			{
				((Transaction)p).setFee(10);
				((Transaction)p).setTotal(p.getAmount() + ((Transaction)p).getFee());
			}
		}
		else if(chance >= 10 && chance < 20) 
		{
			p.setStatus("62");
			p.setShortDesc("в момента нямате задължение");
		}
		else 
		{
			p.setStatus("96");
			p.setShortDesc("обща грешка");
		}
	}

	private Payment invalidFormat()
	{
		Payment p = new Payment();
		p.setStatus("805");
		p.setShortDesc("невалиден формат");
		return p;
	}
		
	public Payment checkBillForPay(Transaction t, String amount)
	{		
		Transaction result = new Transaction();
		
		Integer amountInt = 0; 
		
		if (t == null)
		{
			result.setStatus("58");
			result.setShortDesc("няма такъв тип плащане/ такъв търговец /невалиден код за избиране/");
			return result;
		}
		else if(!t.getStatus().equals("00"))
		{
			return t;
		}
		
		try
		{ 
			amountInt = Integer.parseInt(amount);
		} 
		catch (Exception e)
		{
			return invalidFormat();
		}
		
		int chance = r.nextInt(100);
		
		if (t.getTotal() <= amountInt && chance >= 50)
		{
			result.setStatus("00");
			result.setShortDesc("OK");
		}
		else if(t.getTotal() <= amountInt && chance < 50) 
		{
			result.setStatus("96");
			result.setShortDesc("обща грешка");
		}
		else if(t.getTotal() > amountInt) 
		{
			result.setStatus("13");
			result.setShortDesc("невалидна сума");
		}
		
		return result;
	}

	public Payment checkDeposit(String transactionId, String dateTime, 
			String amount, String merchatId, String subscrNumber)
	{
		Transaction t = checkBillBlocked(transactionId, dateTime, merchatId, subscrNumber);
		
		Integer amountInt;
		
		if(!t.getStatus().equals("00")) 
		{
			return t;
		}
		
		try
		{
			amountInt = Integer.parseInt(amount);
			t.setAmount(amountInt);
		} 
		catch (Exception e)
		{	
			return invalidFormat();
		}
		
		t.setTotal(t.getFee() + t.getAmount());
		return t;
	}
}
