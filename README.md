📖TAR Campus Navigation System
A Java-based campus navigation application using graph data structures and algorithms to help students navigate the TAR campus efficiently.

📋 Table of Contents
Overview
Features
Technologies Used
System Architecture
Installation
Usage
Project Structure
Algorithms Explained
Screenshots
Future Enhancements
Contributing
License
Contact

🎯 Overview
The TAR Campus Navigation System is an interactive application that models campus locations and pathways as a graph data structure. Users can add locations, create connections between them, visualize the campus network, and perform intelligent traversals to explore optimal routes.
This project demonstrates practical implementation of:

Graph data structures (Adjacency List)
Graph traversal algorithms (BFS & DFS)
Object-Oriented Programming principles
MVC (Model-View-Controller) design pattern
JavaFX for GUI visualization

✨ Features
Core Functionality

✅ Add/Remove Locations: Dynamically manage campus vertices
✅ Create/Remove Pathways: Connect locations with bidirectional edges
✅ Graph Visualization: View campus network in console or GUI format
✅ Intelligent Traversal: Explore campus using BFS or DFS algorithms
✅ Input Validation: Robust error handling and user input validation
✅ Interactive Menu: User-friendly console interface

Advanced Features

🎨 JavaFX Visualization: Circular graph layout with visual edges and nodes
🔍 Breadth-First Search (BFS): Level-by-level traversal for shortest paths
🌲 Depth-First Search (DFS): Deep exploration of campus routes
🏗️ MVC Architecture: Clean separation of concerns
💾 Persistent State: Global graph state using Singleton pattern

🛠️ Technologies Used

Language: Java (JDK 8 or higher)
GUI Framework: JavaFX
Data Structures: HashMap, ArrayList, LinkedList, HashSet, Queue
Design Patterns: MVC, Singleton
Algorithms: BFS, DFS


📥 Installation
Prerequisites

Java Development Kit (JDK) 8 or higher
JavaFX SDK (if not included in your JDK)
IDE (IntelliJ IDEA, Eclipse, or VSCode recommended)

📁 Project Structure
tar-campus-navigation/
│
├── Main.java                 # Application entry point
├── Controller.java           # Handles user actions and routing
├── Model.java               # Business logic and user input handling
├── Page.java                # User interface (console menus)
├── Graph.java               # Core graph data structure and algorithms
├── Global.java              # Singleton for shared graph state
├── GraphVisualizer.java     # JavaFX visualization component
└── README.md                # This file

📄 License
This project is licensed under the MIT License - see the LICENSE file for details.
MIT License

Copyright (c) 2025 [Your Name]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction...
