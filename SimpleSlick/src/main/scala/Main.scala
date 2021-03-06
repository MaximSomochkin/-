import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {

  val queryRunner = new QueryRunner(Database.forURL(
    url = "jdbc:postgresql://localhost:5432/dino",
    user = "postgres",
    password = "1234",
    driver = "org.postgresql.Driver"))

  val models = new DinosaurModel
    with EventLogsModel {}

  val dinosaurDAO: DinosaurDAO = new DinosaurDAOImpl(models)

  val dinosaur = Dinosaur(
    id = None,
    weight = 5678.7,
    height = 2.2,
    gender = true,
    isFly = false,
    isSwim = false,
    typeOfFood = TypeOfFood.Omnivores,
    lifespan = Some(15)
  )


  queryRunner.run(dinosaurDAO.update(dinosaur))
  //queryRunner.run(dinosaurDAO.init)

}