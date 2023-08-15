package vendingmachine.util;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import vendingmachine.view.OutputView;


public final class RetryUtil {
    private static final OutputView outputView = OutputView.init();

    private RetryUtil() {
    }

    public static <T> T read(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return read(supplier);
        }
    }

    public static <T, U, R> R read(BiFunction<T, U, R> function, T t, U u) {
        try {
            return function.apply(t, u);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return read(function, t, u);
        }
    }

    public static <T, U> void read(BiConsumer<T, U> function, T t, U u) {
        try {
            function.accept(t, u);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            read(function, t, u);
        }
    }

    public static <T, R> R read(Function<T, R> function, T input) {
        try {
            return function.apply(input);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return read(function, input);
        }
    }
}
