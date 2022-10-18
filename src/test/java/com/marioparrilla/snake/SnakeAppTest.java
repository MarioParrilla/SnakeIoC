package com.marioparrilla.snake;import com.marioparrilla.snake.Annotations.OpenEgg;import com.marioparrilla.snake.Context.ApplicationContext;import com.marioparrilla.snake.Context.SnakeApplication;import com.marioparrilla.snake.ObjectsToTest.Dependency;import com.marioparrilla.snake.ObjectsToTest.Example;import org.junit.jupiter.api.BeforeAll;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;public class SnakeAppTest {    private static ApplicationContext context;    @BeforeAll    public static void init() throws Exception {        context = SnakeApplication.init(SnakeAppTest.class)                .registerCestEggsClass(new Class[]{SnakeAppTest.class, Cest.class})                .classesToScan(new Class[]{SnakeAppTest.class, Dependency.class})                .run();    }    @OpenEgg    public static Dependency dependency;    @Test    public void sayHelloTest() {        dependency.sayHello();    }    @Test    public void getEggByNameWorking() {        assertEquals(Example.class, context.getEgg("exa").getClass());    }    @Test    public void getEggByNameNotWorking() {        assertNull(context.getEgg("e"));    }    @Test    public void getEggByClassWorking() throws Exception {        assertEquals(Example.class, context.getEgg(Example.class).getClass());    }    @Test    public void getEggByClassNotWorking() {        assertThrows(Exception.class, () ->  context.getEgg(String.class));    }    @Test    public void getEggByNameAndClassWorking() throws Exception {        assertEquals(Example.class, context.getEgg("exa" ,Example.class).getClass());    }    @Test    public void getEggByNameAndClassNotWorking() {        assertThrows(Exception.class, () ->  context.getEgg("exa", Dependency.class));    }    @Test    public void containsEggNameWorking() {        assertTrue(context.containsEgg("exa"));    }    @Test    public void containsEggNameNotWorking() {        assertFalse(context.containsEgg("e"));    }    @Test    public void containsEggClassWorking() throws Exception {        assertTrue(context.containsEgg(Example.class));    }    @Test    public void containsEggClassNotWorking() {        assertFalse(context.containsEgg(String.class));    }    @Test    public void containsEggNameAndClassWorking() throws Exception {        assertTrue(context.containsEgg("exa", Example.class));    }    @Test    public void containsEggNameAndClassNotWorking() {        assertFalse(context.containsEgg("exa", Dependency.class));    }}