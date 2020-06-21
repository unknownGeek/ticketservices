package service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.MessageFormat;

import org.codehaus.jackson.annotate.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
public class HelloMessage {

	@JsonProperty("message")
    String message;

    public HelloMessage(String message) {
        this.message = MessageFormat.format("Hi, {0}!", message);
    }
}