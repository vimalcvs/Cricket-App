package com.vimalcvs.myapplication.data


import kotlinx.serialization.Serializable


@Serializable
data class ModelMain(
    val current_offers: CurrentOffers,
    val featured_tournament: FeaturedTournament,
    val upcoming_matches: UpcomingMatches,
    val user_matches: UserMatches,
    val wallet_summary: WalletSummary
)

@Serializable
data class WalletSummary(
    val bonus_balance: Int,
    val cash_balance: Int,
    val total_balance: Int
)

@Serializable
data class UserMatches(
    val match_list: List<MatchXX>
)

@Serializable
data class Teams(
    val a: A,
    val b: B
)

@Serializable
data class UpcomingMatches(
    val match_list: List<Match>,
    val next_page: Int,
    val prev_page: Int?,
)

@Serializable
data class Tournament(
    val match_list: List<Match>,
    val next_page: Int,

    val prev_page: Int?,

    val tournament_id: String,
    val tournament_name: String
)

@Serializable
data class Match(
    val id: String,
    val match_format: String,
    val match_offers: List<MatchOffer>,
    val metadata: Metadata,
    val name: String,
    val starts_at: Long,
    val status: String,
    val teams: Teams,
    val tournament_name: String
)

@Serializable
data class MatchOffer(
    val offer_icon_url: String,
    val subtext: String,
    val title: String
)

@Serializable
data class Metadata(
    val is_lineup_out: Boolean,
    val is_match_initialized: Boolean
)

@Serializable
data class MatchXX(
    val match: Match,
    val top_running_rank: Int?,
    val user_contests: Int,
    val user_teams: Int
)

@Serializable
data class Offer(
    val offer_banner_url: String,
    val type: String
)

@Serializable
data class A(
    val code: String,
    val logo_bg_color: String,
    val logo_url: String,
    val name: String
)

@Serializable
data class B(
    val code: String,
    val logo_bg_color: String,
    val logo_url: String,
    val name: String
)

@Serializable
data class CurrentOffers(
    val offer_list: List<Offer>
)

@Serializable
data class FeaturedTournament(
    val tournament_list: List<Tournament>
)