
package com.programmingexpense.springbootmongodb.handler.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ValidationErrorDTO {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private List<FieldErrorDTO> fieldErrors = new ArrayList<>();

    public ValidationErrorDTO() {
    }

    /**
     * This method is used to get the fieldErrors.
     * @return fieldErrors
     */
    public List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }

    /**
     * @param fieldErrors The value of fieldErrors is to be set here.
     * @return this
     */
    public ValidationErrorDTO setFieldErrors(List<FieldErrorDTO> fieldErrors) {
        this.fieldErrors = fieldErrors;
        return this;
    }

    /**
     * @param errorDTO it is the object of FieldErrorDTO
     */
    public void addError(FieldErrorDTO errorDTO) {
        this.fieldErrors.add(errorDTO);
    }

    /**
     * The method is used to get a String object representing the value of the Number Object.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("fieldErrors = " + fieldErrors)
                .toString();
    }

    /**
     * This is the method that can be used to convert the value as String.
     * @return ""
     */
    public String toJSON() {
        try {
            return MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException ignored) {
        }
        return "";
    }


    public static class FieldErrorDTO {
        private String field;
        private String message;

        public FieldErrorDTO() {
        }

        public FieldErrorDTO(String field, String message) {
            this.field = field;
            this.message = message;
        }

        /**
         * This method is used to get the field.
         * @return field
         */
        public String getField() {
            return field;
        }

        /**
         * @param field The value of field is to be set here.
         * @return this
         */
        public FieldErrorDTO setField(String field) {
            this.field = field;
            return this;
        }

        /**
         * This method is used to get the message.
         * @return message
         */
        public String getMessage() {
            return message;
        }

        /**
         * @param message The value of message is to be set here.
         * @return this
         */
        public FieldErrorDTO setMessage(String message) {
            this.message = message;
            return this;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", "" + "{", "}")
                    .add("field = " + field)
                    .add("message = " + message)
                    .toString();
        }
    }
}
