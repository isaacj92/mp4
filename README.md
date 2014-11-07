Machine Problem 4: Movies and Graphs
===


## Background

In this machine problem, you will design, implement, test and utilize an abstract data type that represents a graph.

A graph is a collection of vertices/nodes and edges. An edge connects two vertices/nodes. Suppose v<sub>1</sub> and v<sub>2</sub> are two vertices then an edge from v<sub>1</sub> to v<sub>2</sub>, also denoted by the pair (v<sub>1</sub>, v<sub>2</sub>), indicates that v<sub>1</sub> can directly be reached from v<sub>2</sub> and vice versa. In a directed graph, edges are one-way. In this case, the edge (v<sub>1</sub>, v<sub>2</sub>) indicates that v<sub>1</sub> is reachable directly from v<sub>2</sub> but v<sub>1</sub> cannot be reached from v<sub>2</sub> along this edge. The children of a vertex/node v are the vertices to which there is an edge from v. 

A path from v<sub>1</sub> to v<sub>2</sub> in a graph is a sequence of edges that starts at vertex v<sub>1</sub> and ends at vertex v<sub>2</sub>. In other words, a path is an ordered list of edges. 

In a multigraph, there can be any number of edges (zero, one, or more) between a pair of nodes.

In a labeled graph, every edge has a label containing information of some sort. Labels are not unique: multiple edges may have the same label. A common label that is used is a real number that represents length/weight of an edge. 

If you want to learn more read Wikipedia's definition of a graph. Then if you still have a question, ask the course staff (instructor or TAs).

Many interesting problems can be represented with graphs. For example:
+ A graph can represent airline flights between cities, where each city is a node and an edge ⟨a,b⟩ indicates that there is a flight from a to b. The edge label might represent the cost in money (airfare), time (length of flight), or distance.
+ To find walking routes across the UBC campus, you can build a graph where nodes represent buildings and other locations and edges represent walking paths connecting two locations. The label/cost of an edge is the physical length of that path.
+ The World Wide Web can be modelled as a graph with node for every webpage and an edge ⟨a,b⟩ if page a links to page b. The label could indicate the anchor text for a link on page a, or the number of links from page a to page b.
+ Facebook is essentially a giant graph with nodes for users and edges between friends. (You can see a [visualization](http://www.yasiv.com/facebook) of the Facebook graph.)

### Context for this Machine Problem

You will work with a data set that represents a collection of movies and their reviews by a set of users. You will construct a graph using the provided data set and then perform some analysis using the graph that you construct. This analysis will include finding the shortest path between two vertices in a graph.

## Key Tasks for MP4

### Understand the Data Set

The data that you will work with for this assignment is in the directory `data`. Here is a description of the files in that directory.

+ `u.data`: The full data set, 100000 ratings by 943 users on 1682 items. Each user has rated at least 20 movies.  Users and items are numbered consecutively from 1.  The data is randomly ordered. Each row has `user id | item id | rating | timestamp`. The time stamps are unix seconds since 1/1/1970 UTC.
+ `u.info`: The number of users, items, and ratings in the data set.
+ `u.item`: Information about the items (movies). Each row has 
`movie id | movie title | release date | video release date | IMDb URL | unknown | Action | Adventure | Animation | Children's | Comedy | Crime | Documentary | Drama | Fantasy | Film-Noir | Horror | Musical | Mystery | Romance | Sci-Fi | Thriller | War | Western |`. The last 19 fields are the genres, a 1 indicates the movie is of that genre, a 0 indicates it is not; movies can be in several genres at once. The movie ids are the ones used in the `u.data` data set.
+ `u.genre`: A list of the genres.
+ `u.user`: Demographic information about the users. Each row has 
`user id | age | gender | occupation | zip code`. The user ids are the ones used in the `u.data` data set.
+ `u.occupation`: A list of the occupations.

### Create the Movie Similarity Graph

All ratings are in the range 1-5. Let us assume that a rating of 1 or 2 for a movie implies that someone did not like the movie, and a rating of 4 or 5 implies that someone liked the movie. For some movie, m, let m.likers be the set of reviewers that liked the movie and let m.dislikers be the set of reviewers that did not like the movie.

Let us quantify the similarity/dissimilarity between two movies using reviewer ratings. 

A dissimilarity score between two movies, m1 and m2, that we will define is 

*w(m1,m2) = 1 + total number of reviewers - ((size of the intersection of m1.likers and m2.likers) +  (size of the intersection of m1.dislikers and m2.dislikers))*.

A lower dissimilarity score indicates that the movies are similar.

Create a weighted, undirected, graph where vertices are movies and edges connect two movies. The weight of an edge  is the dissimilarity score between the two movies that the edge connects.

**You will have to select a representation for the graph ADT and clearly state the representation invariant and the abstraction function.** Graphs are often represented using a list of edges or an [adjacency list](http://en.wikipedia.org/wiki/Adjacency_list) or an [adjacency matrix](http://en.wikipedia.org/wiki/Adjacency_matrix).

### Finding Shortest Paths

The central aspect of this machine problem is to find the similarity/dissimilarity between two movies, and we will define this as being the weight of the shortest path between two movies (which need not be the weight of the direct edge between the movies).

For a weighted, undirected graph, with all edges having positive weights, we can use [Dijkstra’s algorithm](http://en.wikipedia.org/wiki/Dijkstra%27s_algorithm) for finding the shortest path between two vertices. The pseudocode for this algorithm is as follows:

```
 1  function Dijkstra(Graph, source):
 2      dist[source]  := 0                     // Distance from source to source
 3      for each vertex v in Graph:            // Initializations
 4          if v ≠ source
 5              dist[v]  := infinity           // Unknown distance function from source to v
 6              previous[v]  := undefined      // Previous node in optimal path from source
 7          end if 
 8          add v to Q                         // All nodes initially in Q (unvisited nodes)
 9      end for
10      
11      while Q is not empty:                  // The main loop
12          u := vertex in Q with min dist[u]  // Source node in first case
13          remove u from Q 
14          
15          for each neighbor v of u:           // where v has not yet been removed from Q.
16              alt := dist[u] + length(u, v)
17              if alt < dist[v]:               // A shorter path to v has been found
18                  dist[v]  := alt 
19                  previous[v]  := u 
20              end if
21          end for
22      end while
23      return dist[], previous[]
24  end function
```

If we are only interested in a shortest path between vertices `source` and `target`, we can terminate the search at line 13 if `u = target`. Now we can read the shortest path from source to target by reverse iteration:

```
1  S := empty sequence
2  u := target
3  while previous[u] is defined:                // Construct the shortest path with a stack S
4      insert u at the beginning of S           // Push the vertex into the stack
5      u := previous[u]                         // Traverse from target to source
6  end while
```

Now sequence `S` is the list of vertices constituting one of the shortest paths from source to target, or the empty sequence if no path exists.

**You should implement two shortest path methods: one the simply returns the weight/length of the shortest path between two vertices/node, and another that returns the list of vertices on the shortest path.**

## Submission

You will submit your work by pushing all your code to your group’s private BitBucket repository `mp4`.

## Challenge Task

*If you seek an A+*: Implement a method to group movies by similarity. Partition the vertices of a graph into *k* groups (*k* is a parameter to the function) you *maximize the minimum distance between any two partitions.* 

The minimum distance between two partitions is defined as _d<sup>*</sup> := min{d(u,v) : (u,v) ∈ E; u,v are not in the same partition.}_ Here d(u,v) is the distance between u and v, and E is the set of edges in the graph. 

