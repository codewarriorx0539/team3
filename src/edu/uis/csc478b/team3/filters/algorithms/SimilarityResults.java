
package edu.uis.csc478b.team3.filters.algorithms;

/**
 * SimilarityResults: Holds Cosine Similarity and Scaled Cosine Similarity.
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
public class SimilarityResults 
{
    public SimilarityResults(float angle, float scaledAngle )
    {
        this.angle = angle;
        this.scaledAngle = scaledAngle;
    }
    public float angle;
    public float scaledAngle;
}
