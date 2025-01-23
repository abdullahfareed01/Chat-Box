import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AdvancedChatBox {
    public static void main(String[] args) {
        // Frame creation
        JFrame frame = new JFrame("Advanced Chat Box");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components setup
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Message area
        JTextArea messageArea = new JTextArea();
        messageArea.setEditable(false); // Read-only
        messageArea.setBackground(Color.LIGHT_GRAY);
        messageArea.setForeground(Color.BLACK);
        messageArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(messageArea);

        // Input field and buttons
        JTextField inputField = new JTextField();
        JButton sendButton = new JButton("Send");
        JButton clearButton = new JButton("Clear");

        // Buttons panel for multiple buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(sendButton);
        buttonPanel.add(clearButton);

        // Action listener for send button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText().trim();
                if (!message.isEmpty()) {
                    // User's message
                    appendMessage(messageArea, "You: " + message, Color.GREEN);

                    // Simulate response from bot using multithreading
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    executorService.submit(() -> {
                        String botResponse = getBotResponse(message);
                        appendMessage(messageArea, "Bot: " + botResponse, Color.BLUE);
                    });
                    executorService.shutdown();

                    // Clear input field
                    inputField.setText("");
                }
            }
        });

        // Action listener for clear button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageArea.setText(""); // Clear all messages
            }
        });

        // Enter key press functionality
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendButton.doClick();
            }
        });

        // Add components to panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputField, BorderLayout.SOUTH);
        panel.add(buttonPanel, BorderLayout.NORTH);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }

    // Method to append message with synchronized block
    private static synchronized void appendMessage(JTextArea messageArea, String message, Color textColor) {
        try {
            // Change the color of text as per the input
            messageArea.setCaretPosition(messageArea.getDocument().getLength());
            messageArea.append(message + "\n");
            messageArea.setForeground(textColor);
        } catch (Exception ex) {
            System.out.println("Error appending message: " + ex.getMessage());
        }
    }

    // Bot's response logic with Exception Handling and multiple categories
    private static String getBotResponse(String message) {
        try {
            message = message.toLowerCase();

            if (message.contains("hello")) {
                return "Hi! How can I help you?";
            } else if (message.contains("how are you")) {
                return "I'm just a bot, but I'm doing great! 😊";
            } else if (message.contains("bye")) {
                return "Goodbye! Take care!";
            } else if (message.contains("weather")) {
                return "I can't check the weather, but you can use a weather app!";
            } else if (message.contains("time")) {
                return "It's always a good time to chat!";
            } else if (message.contains("name")) {
                return "I am your friendly chat bot!";
            } else if (message.contains("thanks")) {
                return "You're welcome! 😊";
            } else if (message.contains("help")) {
                return "How can I assist you? Feel free to ask!";
            } else if (message.contains("joke")) {
                return "Why don't skeletons fight each other? They don't have the guts!";
            } else if (message.contains("love")) {
                return "Love is the best feeling! Spread kindness and love!";
            } else if (message.contains("music")) {
                return "Music makes the world go round! What type of music do you like?";
            } else if (message.contains("food")) {
                return "Food is essential for life! What's your favorite dish?";
            } else if (message.contains("movies")) {
                return "I love movies! What genre do you enjoy?";
            } else if (message.contains("sports")) {
                return "Sports are awesome! Do you have a favorite game?";
            } else if (message.contains("tech")) {
                return "Tech is fascinating! What gadgets do you like?";
            } else if (message.contains("books")) {
                return "Books are a window to another world. What's your favorite book?";
            } else if (message.contains("travel")) {
                return "Traveling opens new horizons! What's your dream destination?";
            } else if (message.contains("news")) {
                return "I can't read news, but I suggest checking the latest headlines!";
            } else if (message.contains("questions")) {
                return "I'm here to answer your questions. Ask away!";
            } else if (message.contains("advice")) {
                return "Stay positive and keep learning!";
            } else if (message.contains("2+2")) {
                return "10";
            } else if (message.contains("quote")) {
                return "“The only way to do great work is to love what you do.” – Steve Jobs";
            } else {
                return "Sorry, I didn't understand that.";
            }
        } catch (Exception e) {
            return "An error occurred while processing your request.";
        }
    }

    // Example of Generics - You could use a generic method to handle messages or responses dynamically.
    public static <T> String processMessage(T message) {
        return "Processing: " + message.toString();
    }

    // Example of an Immutable class for message handling
    public static class Message {
        private final String content;

        public Message(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }
}