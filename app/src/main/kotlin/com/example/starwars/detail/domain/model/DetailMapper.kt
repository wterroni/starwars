package com.example.starwars.detail.domain.model

import com.example.starwars.data.model.*

interface DetailMapper {
    fun toFilmDetail(filmsModel: FilmsModel): List<Detail>
    fun toPeopleDetail(filmsModel: PeopleModel): List<Detail>
    fun toPlanetsDetail(filmsModel: PlanetsModel): List<Detail>
    fun toSpeciesDetail(filmsModel: SpeciesModel): List<Detail>
    fun toVehiclesDetail(filmsModel: VehiclesModel): List<Detail>
    fun toStarShipsDetail(filmsModel: StarShipsModel): List<Detail>
}

class DetailMapperImpl : DetailMapper {
    override fun toFilmDetail(filmsModel: FilmsModel): List<Detail> {
        return filmsModel.results.map {
                Detail(
                    labelOne = "Title:",
                    textOne = it.title,
                    labelTwo = "Director:",
                    textTwo = it.director,
                    labelThree = "Producer:",
                    textThree = it.producer,
                    labelFour = "Release Date:",
                    textFour = it.producer,
                    labelFive = "",
                    textFive = "",
                    imageUrl = getUrl(it.url, FILMS_CATEGORY)
                )
        }
    }

    override fun toPeopleDetail(filmsModel: PeopleModel): List<Detail> {
        return filmsModel.results.map {
            Detail(
                labelOne = "Name:",
                textOne = it.name,
                labelTwo = "Height:",
                textTwo = it.height,
                labelThree = "Mass:",
                textThree = it.mass,
                labelFour = "Birth Year:",
                textFour = it.birth_year,
                labelFive = "gender:",
                textFive = it.gender,
                imageUrl = getUrl(it.url, PEOPLE_CATEGORY)
            )
        }
    }

    override fun toPlanetsDetail(filmsModel: PlanetsModel): List<Detail> {
        return filmsModel.results.map {
            Detail(
                labelOne = "Name:",
                textOne = it.name,
                labelTwo = "Rotation Period:",
                textTwo = it.rotation_period,
                labelThree = "Orbital Period:",
                textThree = it.orbital_period,
                labelFour = "Diameter:",
                textFour = it.diameter,
                labelFive = "Population:",
                textFive = it.population,
                imageUrl = getUrl(it.url, PLANETS_CATEGORY)
            )
        }
    }

    override fun toSpeciesDetail(filmsModel: SpeciesModel): List<Detail> {
        return filmsModel.results.map {
            Detail(
                labelOne = "Name:",
                textOne = it.name,
                labelTwo = "Classification:",
                textTwo = it.classification,
                labelThree = "Designation:",
                textThree = it.designation,
                labelFour = "Average Height:",
                textFour = it.average_height,
                labelFive = "Language:",
                textFive = it.language,
                imageUrl = getUrl(it.url, SPECIES_CATEGORY)
            )
        }
    }

    override fun toVehiclesDetail(filmsModel: VehiclesModel): List<Detail> {
        return filmsModel.results.map {
            Detail(
                labelOne = "Name:",
                textOne = it.name,
                labelTwo = "Model:",
                textTwo = it.model,
                labelThree = "Manufacturer:",
                textThree = it.manufacturer,
                labelFour = "Length:",
                textFour = it.length,
                labelFive = "Passengers:",
                textFive = it.passengers,
                imageUrl = getUrl(it.url, VEHICLES_CATEGORY)
            )
        }
    }

    override fun toStarShipsDetail(filmsModel: StarShipsModel): List<Detail> {
        return filmsModel.results.map {
            Detail(
                labelOne = "Name:",
                textOne = it.name,
                labelTwo = "Model:",
                textTwo = it.model,
                labelThree = "Manufacturer:",
                textThree = it.manufacturer,
                labelFour = "Length:",
                textFour = it.length,
                labelFive = "Passengers:",
                textFive = it.passengers,
                imageUrl = getUrl(it.url, STARSHIPS_CATEGORY)
            )
        }
    }

    private fun getUrl(url: String, categoryMask: String): String {
        val number = url.replace(Regex("[^0-9]"), "")
        return IMAGE_URL.replace(CATEGORY_URL, categoryMask).replace(NUMBER_URL, number)
    }

    companion object {
        private const val NUMBER_URL = "{number}"
        private const val CATEGORY_URL = "{category}"
        private const val IMAGE_URL = "https://starwars-visualguide.com/assets/img/{category}/{number}.jpg"

        private const val PEOPLE_CATEGORY = "characters"
        private const val PLANETS_CATEGORY = "planets"
        private const val FILMS_CATEGORY = "films"
        private const val SPECIES_CATEGORY = "species"
        private const val VEHICLES_CATEGORY = "vehicles"
        private const val STARSHIPS_CATEGORY = "starships"
    }

}

