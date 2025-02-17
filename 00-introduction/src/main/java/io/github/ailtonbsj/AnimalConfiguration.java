package io.github.ailtonbsj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalConfiguration {
    @Bean(name = "dog")
    public Animal dog(){
        return new Animal(){
            @Override
            public void doNoise() {
                System.out.print("Wof!");
            }
        };
    }

    @Bean(name = "cat")
    public Animal cat() {
        return new Animal() {
            @Override
            public void doNoise() {
                System.out.print("Meow!");
            }
        };
    }
}
