Machine Problem 4: Movies and Graphs
===


## Background

In this machine problem, you will design, implement, test and utilize an abstract data type that represents a graph.

A graph is a collection of vertices/nodes and edges. An edge connects two vertices/nodes. Suppose v<sub>1</sub> and v<sub>2</sub> are two vertices then an edge from v1 to v2, also denoted by the pair (v<sub>1</sub>, v<sub>2</sub>), indicates that v<sub>1</sub> can directly be reached from v<sub>2</sub> and vice versa. In a directed graph, edges are one-way. In this case, the edge (v<sub>1</sub>, v<sub>2</sub>) indicates that v<sub>1</sub> is reachable directly from v<sub>2</sub> but v<sub>1</sub> cannot be reached from v<sub>2</sub> along this edge. The children of a vertex/node v are the vertices to which there is an edge from v. 

A path from v<sub>1</sub> to v<sub>2</sub> in a graph is a sequence of edges that starts at vertex v<sub>1</sub> and ends at vertex v<sub>2</sub>. In other words, a path is an ordered list of edges. 

In a multigraph, there can be any number of edges (zero, one, or more) between a pair of nodes.

In a labeled graph, every edge has a label containing information of some sort. Labels are not unique: multiple edges may have the same label. A common label that is used is a real number that represents length/weight of an edge. 

If you want to learn more read Wikipedia's definition of a graph. Then if you still have a question, ask the course staff (instructor or TAs).

Many interesting problems can be represented with graphs. For example:
+ A graph can represent airline flights between cities, where each city is a node and an edge ⟨a,b⟩ indicates that there is a flight from a to b. The edge label might represent the cost in money (airfare), time (length of flight), or distance.
+ To find walking routes across the UBC campus, you can build a graph where nodes represent buildings and other locations and edges represent walking paths connecting two locations. The label/cost of an edge is the physical length of that path.
+ The World Wide Web can be modelled as a graph with node for every webpage and an edge ⟨a,b⟩ if page a links to page b. The label could indicate the anchor text for a link on page a, or the number of links from page a to page b.
+ Facebook is essentially a giant graph with nodes for users and edges between friends. (You can see a visualization of the Facebook graph.)

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

Create a weighted, undirected, graph where the weight of an edge  is the dissimilarity score between the two movies that the edge connects.