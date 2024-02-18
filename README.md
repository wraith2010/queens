```
8 |__|♕|__|##|__|##|__|##|
7 |##|__|##|♕|##|__|##|__|
6 |__|##|__|##|__|♕|__|##|
5 |##|__|##|__|##|__|##|♕|
4 |__|##|♕|##|__|##|__|##|
3 |♕|__|##|__|##|__|##|__|
2 |__|##|__|##|__|##|♕|##|
1 |##|__|##|__|♕|__|##|__|
   a  b  c  d  e  f  g  h
```

My exploration on the n queens problem

Finding 1 solution has basically been solved
Monte Carlo approaches have been explored

My goal is to find an efficient solution that guarantees all solutions are found without having to brute force all combinations. I believe there solution where previous solutions for the value of N can be used to limit the number of possibles that need to be created for the current value of N. An ancestral approach.  The goal is to be simpler than the current Latin Square solutions.

# N Queens
The N-Queen Problem is a classic puzzle that has been around for a long time but has experienced more attention since the development of computer science. Many algorithms have been developed to try to solve the problem as efficiently as possible, and it remains a point of fascination for many computer scientists. Discover how the problem is set up and common approaches to solving it in the rest of this article.

## What Is the N-Queen Problem?
In a nutshell, the N-Queen Problem is a chess problem. The objective is to find all possible solutions for placing N number of queens on an N x N chessboard so that none of the queens can attack (or “clash”) with each other. Because queens can move in all possible directions, i.e. diagonally and orthogonally, this is a fairly complicated and intriguing problem to solve.

The origins of this well-studied problem are debated, but similar puzzles have been detailed as early as the 8th century. The current problem as we know it, however, became popular after the 19th century. Since the advent of computer science in the 70s, the problem has gained traction, and many algorithms have been designed in an attempt to solve the problem most optimally.
