package com.mattstine.dddworkshop.pizzashop.kitchen;

import com.mattstine.dddworkshop.pizzashop.infrastructure.events.adapters.InProcessEventLog;
import com.mattstine.dddworkshop.pizzashop.infrastructure.events.ports.EventLog;
import com.mattstine.dddworkshop.pizzashop.infrastructure.events.ports.Topic;
import com.mattstine.dddworkshop.pizzashop.infrastructure.repository.ports.Aggregate;
import com.mattstine.dddworkshop.pizzashop.infrastructure.repository.ports.AggregateState;
import com.mattstine.dddworkshop.pizzashop.ordering.OrderRef;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.util.function.BiFunction;

@Value
public final class Pizza implements Aggregate {
    PizzaRef ref;
    OrderRef orderRef;
    Size size;
    EventLog $eventLog;
    @NonFinal
    State state;

    @Builder
    private Pizza(@NonNull PizzaRef ref,
                  @NonNull OrderRef orderRef,
                  @NonNull Size size,
                  @NonNull EventLog eventLog) {
        this.ref = ref;
        this.orderRef = orderRef;
        this.size = size;
        this.$eventLog = eventLog;
    }

    /**
     * Private no-args ctor to support reflection ONLY.
     */
    private Pizza() {
        this.ref = null;
        this.orderRef = null;
        this.size = null;
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

    @Override
    public Pizza identity() {
        return null;
    }

    @Override
    public BiFunction<Pizza, PizzaEvent, Pizza> accumulatorFunction() {
        return new Accumulator();
    }

    @Override
    public PizzaRef getRef() {
        return ref;
    }

    @Override
    public PizzaState state() {
        return new PizzaState(ref, orderRef, size);
    }

    enum Size {
        IDENTITY, SMALL, MEDIUM, LARGE
    }

    enum State {
        NEW,
        PREPPING,
        PREPPED,
        BAKING,
        BAKED
    }

    static class Accumulator implements BiFunction<Pizza, PizzaEvent, Pizza> {

        @Override
        public Pizza apply(Pizza pizza, PizzaEvent pizzaEvent) {
            return null;
        }
    }

    @Value
    static class PizzaState implements AggregateState {
        PizzaRef ref;
        OrderRef orderRef;
        Size size;
    }
}
