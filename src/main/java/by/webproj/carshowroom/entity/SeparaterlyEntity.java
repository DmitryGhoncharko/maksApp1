package by.webproj.carshowroom.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public class SeparaterlyEntity {
    private final Long id;
    private final String sepName;
    private final double sepWeight;

    private SeparaterlyEntity(Builder builder) {
        id = builder.id;
        sepName = builder.suspensionName;
        sepWeight = builder.suspensionWeight;
    }

    public static class Builder {
        private Long id;
        private String suspensionName;
        private double suspensionWeight;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String engineName) {
            this.suspensionName = engineName;
            return this;
        }

        public Builder withWeight(double engineWeight) {
            this.suspensionWeight = engineWeight;
            return this;
        }

        public SeparaterlyEntity build() {
            return new SeparaterlyEntity(this);
        }
    }
}
