
package edu.uis.csc478b.team3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 
 * @author Jake
 */
public class Graph 
{
    // Inner class to represent a vertex in a graph
    class Vertex 
    {
        public HashSet<String> adjacencies;
        
        public Vertex()
        {
            adjacencies = new HashSet<>();
        }
        
        public Vertex(String head)
        {
            adjacencies = new HashSet<>();
            add(head);
        }
        
        void add(String head)
        {
            adjacencies.add(head);
        }
    }
    
    protected Map<String, Vertex> graph;

    /**
     * Graph Default Constructor
     */
    public Graph()
    {
        graph = new HashMap<>();
    }
    
    /**
     * addAdjacency Connect vertices from the tail to the head. If the vertex
     * is not in the graph its added.
     * 
     * @param tail
     * @param head 
     */
    public void addAdjacency(String tail , String head )
    {
        if(graph.containsKey(tail) == true)
        {
            graph.get(tail).add(head);
        }
        else
        {
            graph.put(tail, new Vertex(head));
        }
        
        if(!graph.containsKey(head))
        {
            graph.put(head, new Vertex());
        }
    }
    
    /**
     * Topological sort: Dependency is found by calculating the in degree of vertices
     * and associated adjacencies.
     * 
     * in degree: since this is a DAG the in degree is the number of vertices that
     * point to a specific vertex.
     * 
     * @param list Returns a list of topologically sorted items
     * @throws Exception 
     */
    public void topologicalSort(ArrayList<String> list) throws Exception
    {
        Map<String, Integer> inDegreeMap = new HashMap<>();
        Queue<String> noEdges = new LinkedList<>();
        
        // Calculate the in degree by observing the adjacencies
        for(Map.Entry entry : graph.entrySet()) 
        {
            String key = (String) entry.getKey();
            if(!inDegreeMap.containsKey(key))
            {
                inDegreeMap.put(key, 0);      
            }
            
            Vertex v = (Vertex) entry.getValue();
            for(String s : v.adjacencies)
            {
                if(inDegreeMap.containsKey(s))
                {
                    inDegreeMap.put( s, inDegreeMap.get(s) + 1);
                }
                else
                {
                    inDegreeMap.put(s, 1);      
                }
            }
        }
        
        // Find the least dependant items that don't have any in degrees
        for (Map.Entry entry : inDegreeMap.entrySet()) 
        {
            Integer inDegree = (Integer) entry.getValue();
            if(inDegree == 0)
            {
                noEdges.add((String) entry.getKey());  
            }
        }
        
        // Walk the vertices while looking for new vertices that don't have dependencies
        while(!noEdges.isEmpty())
        {
            String key = noEdges.poll();
            list.add(key);
            inDegreeMap.remove(key);
            
            for (String adjacency : graph.get(key).adjacencies) 
            {
                inDegreeMap.put( adjacency, inDegreeMap.get(adjacency) - 1);
                if(inDegreeMap.get(adjacency) == 0)
                {
                    inDegreeMap.remove(adjacency);
                    noEdges.add(adjacency);
                }
            }
        }

        // This should NEVER happen per the requirements 
        if(inDegreeMap.size() > 0)
        {
            throw new Exception("Graph::topologicalSort A cycle was found");
        }

        Collections.reverse(list);
    }

}
  
