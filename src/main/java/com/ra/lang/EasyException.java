package com.ra.lang;

import com.ra.util.ThrowingConsumer;

import java.util.function.Consumer;

public class EasyException {

    /**
     * Since {@link ThrowingConsumer} interface uses generics, we can easily handle any specific exception.
     * Let's see how to use it in practice:
     * <pre>
     *     {@code
     *     List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, 20);
     *     integers.forEach(throwingConsumerWrapper(i -> writeToFile(i), IOException.class));
     *     }
     * </pre>
     *
     * @param throwingConsumer {@link ThrowingConsumer<T , E extends Exception>}
     * @param exceptionClass {@link Class<E>}
     * @param <T> T
     * @param <E> E
     * @return {@link Consumer<T>}
     */
    public static <T , E extends Exception> Consumer<T> throwingConsumerWrapper
            (ThrowingConsumer<T, E> throwingConsumer , Class<E> exceptionClass)
    {
        return i ->
        {
            try {
                throwingConsumer.accept(i);
            }catch(Exception e)
            {
                try {
                    E exCast = exceptionClass.cast(e);
                    System.out.println("Exception occurred: " + exCast.getMessage());
                }catch (ClassCastException ccEx)
                {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
