/**
 * Package that contains the {@link CubeDB.ReadXML} and {@link CubeDB.WriteXML} classes which read and write score data in an XML file.
 * 
 * <p>
 * The {@link CubeDB.ReadXML} class reads the data from the XML file using the {@link CubeDB.ReadXML#getData()} method.
 * </p>
 * <p>
 * The {@link CubeDB.WriteXML} class writes the data inside the XML file by creating the {@code temp.xml} file with the
 * {@link CubeDB.WriteXML#createXml(String,String)} method, then merging it with the {@code score.xml} file using the
 * {@link CubeDB.WriteXML#mergeXml(File)} method.
 * </p>
 * <p>
 * The {@code temp.xml} file contains the current player's data while the {@code score.xml}
 * contains the leaderboard. If the {@code score.xml} file gets deleted, the {@link CubeDB.WriteXML#createInitXml(File)} method creates a
 * new empty leaderboard.
 * </p>
 */
package CubeDB;
