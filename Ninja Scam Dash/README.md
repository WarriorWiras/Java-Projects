# 🥷💎 Ninja Scam Dash 

**Ninja Scam Dash** is an exciting educational 2D game designed to **raise awareness about scam prevention** through interactive gameplay. Players control a ninja, navigate obstacles, collect power-ups, and answer scam-prevention questions to progress.  
This game was developed to **test and showcase a custom-built game engine**, designed following strict **Object-Oriented Programming (OOP)** and **SOLID design principles**, using **Java** and **Gradle** for project management.

Our main goal was not only to create a fun and engaging game but also to design and implement a flexible, reusable, and scalable **Game Engine**. 🎮✨

> 📌 **Developed as part of an Object-Oriented Programming project at [Singapore Institute of Technology] (2025).**

---

## 🌟 Game Highlights

- 🚀 Fast-paced ninja gameplay with scam prevention education
- 🎯 Collision-triggered quiz challenges
- 🎮 Fully customizable controls (WASD and beyond)
- 🔥 Randomized spawning of obstacles and power-ups
- 🎵 Background music and sound effects managed with a Singleton AudioManager
- 🖼️ Dynamic scene transitions (Menu, Game, Pause, Win, Game Over, Questions)
- 💖 Health and Diamond UI display for real-time player feedback

---

## ⚙️ Tech Stack

### 🖥️ Core Technologies
- **Java**
- **Gradle** (Project management)
- **LibGDX** (2D game development)
- **VS Code with Gradle for Java Extension or Eclipse IDE for Java Developers** (IDE)

### 🎨 Design Patterns Implemented
- **Factory Method**: Entity creation (Obstacles, Diamonds, Power-Ups)
- **Strategy Pattern**: Flexible collision handling
- **Singleton Pattern**: AudioManager

> 🎯 Special thanks to the Gradle and LibGDX communities for providing amazing tools that made this project possible!

---

## 🧩 Game Engine Features

- **Flexible Collision Management**  
  → Each entity (Obstacle, Diamond, PowerUp) has its own collision behavior.

- **Scene Management System**  
  → Smooth screen transitions using an IScreen interface and SceneManager.

- **Input Manager**  
  → Rebindable keys with real-time updates.

- **Audio Manager**  
  → Sound control without redundant resource loading.

- **Entity Manager & Spawning System**  
  → Randomized spawning of obstacles and power-ups.

---

## 🎮 Gameplay Overview

- 🏃 Dodge obstacles while collecting diamonds and scrolls!
- 📚 Answer scam prevention questions when collecting items.
- ❤️ Correct answers = Bonus health or progress.
- 💀 Wrong answers = Health loss!
- 🏆 Collect and pass 3 diamond quizzes to WIN!

---

## 🚧 Known Limitations

- 🔇 No in-game audio volume/mute control yet
- 🎚️ No dynamic difficulty scaling
- 🎮 Controller (Gamepad) support not available (keyboard only)

---

## 🛠️ Getting Started

1. **Clone the repository**:
   ```bash
   git clone <repository-link>
   ```

2. **Build the project**:
   ```bash
   ./gradlew build
   ```

3. **Run the game**:
   ```bash
   ./gradlew run
   ```

Enjoy playing and learning! 🎮📚

---

## 🙏 Special Thanks

We would like to thank:
- [Gradle](https://gradle.org/) for seamless project building.
- [LibGDX](https://libgdx.com/) for providing a powerful framework for 2D games.
- All scam prevention initiatives whose spirit inspired our game's educational mission.

---

## 📜 License

This project was created for educational and academic purposes.  
It is intended for learning, demonstration, and showcasing **Object-Oriented Programming** skills.

---
