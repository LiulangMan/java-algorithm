package _设计模式._行为型._策略模式;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/11/1 11:08
 */


interface PromotionStrategy {
    default void doPromotion() {
        System.out.println("接口 doPromotion");
    }
}

class CouponStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("Counup doPromotion");
    }
}


class Cashbacktrategy implements PromotionStrategy {
}

class GroupbuyStrategy implements PromotionStrategy {
}

class EmptyStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("Empty doPromotion");
    }
}

public class PromotionStrategyFactory {

    private static Map<String, PromotionStrategy> PROMOTION_STRATEGY_MAP = new HashMap<>();

    private static EmptyStrategy emptyStrategy = new EmptyStrategy();

    static {

        PROMOTION_STRATEGY_MAP.put(PromotionKey.COUPON, new CouponStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.CASHBACK, new Cashbacktrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.GROUPBUY, new GroupbuyStrategy());
    }


    private interface PromotionKey {
        String COUPON = "COUPON";
        String CASHBACK = "CASHBACK";
        String GROUPBUY = "GROUPBUY";
    }

    private PromotionStrategyFactory() {
    }

    public static PromotionStrategy getPromotionStrategy(String promotionKey) {
        PromotionStrategy promotionStrategy = PROMOTION_STRATEGY_MAP.get(promotionKey);

        return promotionStrategy == null ? emptyStrategy : promotionStrategy;
    }

    public static void main(String[] args) {
        PromotionStrategy coupon = PromotionStrategyFactory.getPromotionStrategy("COUPON111");
        coupon.doPromotion();
    }

}
