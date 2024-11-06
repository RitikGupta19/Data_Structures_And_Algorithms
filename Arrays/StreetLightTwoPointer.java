/*
 * There are some objects placed on a number line. You are given their coordinates in ascending order as an array of integers objects. There are no two objects placed on the same coordinate.

Your task is to place a lamp on an integer coordinate on the same line so that it illuminates the maximal number of objects. The lamp placed at a coordinate c illuminates everything around it within a radius of radius, i.e. within a range [c - radius, c + radius] (inclusive). The lamp can be placed on any integer coordinate, even if there's an object on the coordinate.

Return the coordinate to place the lamp so that it illuminates the maximal number of objects. In the case that there is more than one such coordinate, return the one among them with the minimal coordinate.

Example

For objects = [-5, 3, 4, 9] and radius = 5, the output should be solution(objects, radius) = -1.
 */
public class StreetLightTwoPointer {
    public static int solution(int[] objects, int radius) {
        int maxIlluminated = 0;  // Track the maximum number of objects illuminated
        int bestCoordinate = Integer.MAX_VALUE;  // Track the best coordinate (smallest)

        // Sliding window approach
        int n = objects.length;
        int left = 0;

        // Iterate over each object, treating it as the center for the lamp
        for (int i = 0; i < n; i++) {
            int center = objects[i];

            // Move the left pointer to ensure objects[left] is within [center - radius, center + radius]
            while (objects[left] < center - radius) {
                left++;
            }

            // Now objects between left and i (inclusive) are illuminated
            int illuminatedCount = i - left + 1;

            // Check if this configuration illuminates more objects, or if the same number, prefers a smaller coordinate
            if (illuminatedCount > maxIlluminated) {
                maxIlluminated = illuminatedCount;
                bestCoordinate = center;
            } else if (illuminatedCount == maxIlluminated) {
                // If the same number of objects are illuminated, pick the smaller coordinate
                bestCoordinate = Math.min(bestCoordinate, center);
            }
        }

        return bestCoordinate;
    }
}
