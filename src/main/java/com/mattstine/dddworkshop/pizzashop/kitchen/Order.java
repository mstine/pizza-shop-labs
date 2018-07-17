package com.mattstine.dddworkshop.pizzashop.kitchen;

import com.mattstine.dddworkshop.pizzashop.infrastructure.events.adapters.InProcessEventLog;
import com.mattstine.dddworkshop.pizzashop.infrastructure.events.ports.EventLog;
import com.mattstine.dddworkshop.pizzashop.infrastructure.events.ports.Topic;
import com.mattstine.dddworkshop.pizzashop.infrastructure.repository.ports.Aggregate;
import com.mattstine.dddworkshop.pizzashop.infrastructure.repository.ports.AggregateState;
import com.mattstine.dddworkshop.pizzashop.ordering.OrderRef;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.util.List;
import java.util.function.BiFunction;

@Value
public final class Order implements Aggregate {
    KitchenOrderRef ref;
    OrderRef orderRef;
    List<Pizza> pizzas;
    EventLog $eventLog;
    @NonFinal
    State state;

    @Builder
    private Order(@NonNull KitchenOrderRef ref, @NonNull OrderRef orderRef, @Singular List<Pizza> pizzas, @NonNull EventLog eventLog) {
        this.ref = ref;
        this.orderRef = orderRef;
        this.pizzas = pizzas;
        this.$eventLog = eventLog;
    }

    /**
     * Private no-args ctor to support reflection ONLY.
     */
    private Order() {
        this.ref = null;
        this.orderRef = null;
        this.pizzas = null;
        this.$eventLog = null;
    }

    public boolean isNew() {
        return false;
    }

    public void startPrep() {

    }

    public boolean isPrepping() {
        return false;
    }

    public void finishPrep() {

    }

    public boolean hasFinishedPrep() {
        return false;
    }

    public void startBake() {

    }

    public boolean isBaking() {
        return false;
    }

    public void finishBake() {

    }

    public boolean hasFinishedBaking() {
        return false;
    }

    public void startAssembly() {

    }

    public boolean hasStartedAssembly() {
        return false;
    }

    public void finishAssembly() {

    }

    public boolean hasFinishedAssembly() {
        return false;
    }

    @Override
    public Order identity() {
        return null;
    }

    @Override
    public BiFunction<Order, OrderEvent, Order> accumulatorFunction() {
        return new Accumulator();
    }

    @Override
    public OrderState state() {
        return new OrderState(ref, orderRef, pizzas);
    }

    enum State {
        NEW,
        PREPPING,
        PREPPED,
        BAKING,
        BAKED,
        ASSEMBLING,
        ASSEMBLED
    }

    static class Accumulator implements BiFunction<Order, OrderEvent, Order> {

        @Override
        public Order apply(Order order, OrderEvent orderEvent) {
            return null;
        }
    }

    /*
     * Pizza Value Object for Order Details Only
     */
    @Value
    static final class Pizza {
        Size size;

        @Builder
        private Pizza(@NonNull Size size) {
            this.size = size;
        }

        enum Size {
            SMALL, MEDIUM, LARGE
        }
    }

    @Value
    static class OrderState implements AggregateState {
        KitchenOrderRef ref;
        OrderRef orderRef;
        List<Pizza> pizzas;
    }
}
