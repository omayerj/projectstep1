package coupon.system;

import java.sql.Date;
import java.util.Collection;

import DBDAO.CouponDBDAO;
import DBDAO.JoinTableDBDAO;
import object.java.bens.Coupon;

public class DailyCoupounExpirationTask implements Runnable {

	private JoinTableDBDAO JoinTableDBdao = new JoinTableDBDAO();
	private CouponDBDAO couponDBdao = new CouponDBDAO();

	private boolean quit = false;

	@Override
	public void run() {
		int i = 0;
		while (quit) {
			System.out.println("DailyCoupounExpirationTask");
			System.out.println(i);
			System.out.println("CompanyFacade::getCouponsWithMaxEndDate");
			Date now = new Date(System.currentTimeMillis());
			Collection<Coupon> lestCouponS = JoinTableDBdao.getCouponsLessFromNow(now);
		/*
		 * delete coupon from 3 table 
		 */
			//TODO
			/*
				need to add for the reale porject 
				to make daily task work and delete
				###################### 
			for (Coupon coupon : lestCouponS) {
				JoinTableDBdao.deleteCoupon(coupon);
				couponDBdao.removeCoupon(coupon);
				
			}
			 */			
			try {
				Thread.sleep(1 * // minutes to sleep
						60 * // seconds to a minute
						1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}

	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}

	public DailyCoupounExpirationTask() {
		quit = true;

	}

	public void stopTask() {
		quit = false;
	}

}
