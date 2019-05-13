/**
 * Package which contains the classes responsible for the core functions of the game, such as
 * {@link CubEControllers.GameSceneController#moveBat(String)}, {@link CubEControllers.GameSceneController#ballMovement()},
 * {@link CubEControllers.GameSceneController#collisionCheck()} and also ending the game with
 * {@link CubEControllers.GameSceneController#switchToEndScene()}.
 * 
 * 
 * <p>
 * The {@link CubEControllers.GameSceneController#moveBat(String)} method moves the bat by 10 pixels using the arrow keys.
 * </p>
 * <p>
 * The {@link CubEControllers.GameSceneController#ballMovement()} method moves the ball using a {@code ballAnim} AnimationTimer.
 * </p>
 * <p>
 * The {@link CubEControllers.GameSceneController#collisionCheck()} method checks if the ball hit either a border, a brick or the bat,
 * with {@link CubEControllers.GameSceneController#checkBorderHit(Circle)},
 * {@link CubEControllers.GameSceneController#checkBrickHit(Circle,Rectangle,int)}
 * and {@link CubEControllers.GameSceneController#checkBatHit(Circle,Rectangle)}.
 * </p>
 * <p>
 * The {@link CubEControllers.GameSceneController#switchToEndScene()} method stops the {@code ballAnim} and also resets the {@code score}
 * and {@code lives} for the next game.
 * </p>
 */
package CubEControllers;