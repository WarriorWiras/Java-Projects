# Ninja Scam Dash

## About The Project

**Ninja Scam Dash** is an exciting educational 2D game designed to **raise awareness about scam prevention** through interactive gameplay. Players control a ninja, navigate obstacles, collect power-ups, and answer scam-prevention questions to progress.  
This game was developed to **test and showcase a custom-built game engine**, designed following strict **Object-Oriented Programming (OOP)** and **SOLID design principles**, using **Java** and **Gradle** for project management.

Our main goal was not only to create a fun and engaging game but also to design and implement a flexible, reusable, and scalable **Game Engine**.

---

## Built With

- **Java**
- **Gradle** (for project building and dependency management)
- **LibGDX** (framework for cross-platform game development)

Special thanks to the creators of **Gradle** for providing an efficient build system that streamlined our project management.

---

## Game Features

- **Educational Gameplay**: Answer scam-related questions triggered by collecting scrolls and diamonds.
- **Collision System**: Refactored collision management using the **Strategy Pattern** for flexible and scalable handling.
- **Scene Management**: Interface-segregated scene system for clean transitions between menu, game, question, pause, and result screens.
- **Input Management**: Fully customizable key bindings at runtime.
- **Audio Management**: Centralized background music and sound effects system using the **Singleton Pattern**.
- **Entity Spawning**: Centralized and extensible spawning of obstacles and power-ups via the **Factory Pattern**.
- **UI System**: Real-time health and diamond collection indicators through a modular UI Manager.
- **Scam Quiz Mechanism**: Players must correctly answer questions to receive health boosts or collect diamonds.
- **Win Condition**: Collect and correctly answer three diamond-triggered questions to win!

---

## Game Engine Highlights

Our game engine was built with a focus on:

- **Scalability**: Easily add new entities, screens, or systems without modifying existing structures.
- **Reusability**: Reusable collision handlers, input systems, and scene management modules.
- **Maintainability**: Clear separation of concerns using OOP best practices and SOLID principles.

**Key Design Patterns Used:**
- **Singleton Pattern**: For AudioManager, ensuring a single instance throughout the game.
- **Factory Method Pattern**: For creating game entities dynamically at runtime.
- **Strategy Pattern**: For handling different collision behaviors modularly.

---

## Limitations and Future Improvements

- **No In-Game Audio Controls**: (e.g., mute, volume adjustments).
- **No Dynamic Difficulty Adjustment**: Difficulty does not scale based on player skill.
- **No Gamepad/Controller Support**: Currently only supports keyboard input.

---

## Getting Started

1. **Clone the repository**:
   ```bash
   git clone <repository-link>
   ```

2. **Build the project using Gradle**:
   ```bash
   ./gradlew build
   ```

3. **Run the game**:
   ```bash
   ./gradlew run
   ```

---

## License

This project is created as part of an academic module for **Object-Oriented Programming** coursework.  
It is primarily for educational and demonstration purposes.
