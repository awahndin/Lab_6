package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.GameRuleCardsDomainModel;
import util.HibernateUtil;

public class GameRuleCardsDAL   {

	public static ArrayList<GameRuleCardsDomainModel> getCardsRules() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		ArrayList<GameRuleCardsDomainModel> RuleCards = new ArrayList<GameRuleCardsDomainModel>();

		try {
			tx = session.beginTransaction();

			List rules = session.createQuery("FROM GameRuleCardsDomainModel ORDER BY RULEID, PICKORDER").list();
			for (Iterator iterator = rules.iterator(); iterator.hasNext();) {
				GameRuleCardsDomainModel gr = (GameRuleCardsDomainModel) iterator.next();
				RuleCards.add(gr);

			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return RuleCards;

	}
	
}
