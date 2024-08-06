package dev.wy.omaniollama.functions;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/**")
    public String joke() {
        return chatClient.prompt().user("Please tell me a bad joke about computers")
                .call()
                .content();
    }
}
