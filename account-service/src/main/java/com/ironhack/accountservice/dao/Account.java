package com.ironhack.accountservice.dao;

import com.ironhack.accountservice.exceptions.EmptyStringException;
import com.ironhack.accountservice.exceptions.ExceedsMaxLength;
import com.ironhack.accountservice.exceptions.InvalidCountryException;
import com.ironhack.accountservice.exceptions.NameContainsNumbersException;
import com.ironhack.accountservice.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Industry industry;
    @Column(name="employee_count")
    private Integer employeeCount;

    private String city;
    private String country;


    private static final String colorMain = "\u001B[33m";
    private static final String colorMainBold = "\033[1;37m";
    private static final String colorTable = "\u001B[32m";
    private static final String colorHeadlineBold = "\033[1;34m";
    private static final String reset = "\u001B[0m";


    public Account(Industry industry, int employeeCount, String city, String country) throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength {
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
    }


    public void setEmployeeCount(int employeeCount) throws ExceedsMaxLength {
        if (employeeCount <= 0) {
            throw new IllegalArgumentException("Employee count must be positive. Please try again.");
        }

        this.employeeCount = employeeCount;
    }


    public void setCity(String city) throws EmptyStringException, NameContainsNumbersException, IllegalArgumentException, ExceedsMaxLength {
        if (city.isEmpty()) {
            throw new EmptyStringException("No city input. Please try again.");
        }
        else if(!city.matches("[a-zA-Z\\u00C0-\\u00FF]+")){
            throw new NameContainsNumbersException( "City can not contain numbers. Please try again.");

        } else if(city.length()>25){
            throw new ExceedsMaxLength( "Exceeds maximum value of 25 characters. Please try again.");
        }

        this.city = city;
    }

    public void setCountry(String country) throws InvalidCountryException, EmptyStringException, ExceedsMaxLength {

        if (country.isEmpty()) {
            throw new EmptyStringException("No country input. Please try again.");
        }
        else if(country.length()>25){
            throw new ExceedsMaxLength("Exceeds maximum value of 25 characters. Please, try again.");
        }
        else if(!isValidCountry(country)){
            throw new InvalidCountryException( "That is not a real country. Please try again.");
        }


        this.country = country;
    }


    @Override
    public String toString() {
        System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "New Account created" + colorMain + " ════════════════╦═══════════════════════════╦═══════════════════════════╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-27s %-1s %-24s %-1s %-32s %-1s %-32s %-1s\n",
                colorMain + "║",
                colorHeadlineBold + "ID",
                colorMain + "║",
                colorHeadlineBold + "Industry",
                colorMain + "║",
                colorHeadlineBold + "Employee Count",
                colorMain + "║",
                colorHeadlineBold + "City",
                colorMain + "║",
                colorHeadlineBold + "Country",
                colorMain + "║\n" +
                        colorMain + "╠════════════╬══════════════════════╬═══════════════════╬═══════════════════════════╬═══════════════════════════╣");
        return  colorMain +
                String.format("%-1s %-15s %-1s %-25s %-1s %-22s %-1s %-30s %-1s %-30s %-1s\n",
                        colorMain + "║",
                        colorTable + id,
                        colorMain + "║",
                        colorTable + industry,
                        colorMain + "║",
                        colorTable + employeeCount,
                        colorMain + "║",
                        colorTable + city,
                        colorMain + "║",
                        colorTable + country,
                        colorMain + "║") + reset;
    }

    //Countries list as per ISO
    private static String[] countries = {
            "ANDORRA", "UNITED ARAB EMIRATES", "AFGHANISTAN", "ANTIGUA & BARBUDA", "ANGUILLA",
            "ALBANIA", "ARMENIA", "ANGOLA", "ANTARCTICA", "ARGENTINA", "AMERICAN SAMOA", "AUSTRIA",
            "AUSTRALIA", "ARUBA", "ÅLAND ISLANDS", "AZERBAIJAN", "BOSNIA & HERZEGOVINA", "BARBADOS",
            "BANGLADESH", "BELGIUM", "BURKINA FASO", "BULGARIA", "BAHRAIN", "BURUNDI", "BENIN", "ST BARTHÉLEMY",
            "BERMUDA", "BRUNEI", "BOLIVIA", "CARIBBEAN NETHERLANDS", "BRAZIL", "BAHAMAS", "BHUTAN", "BOUVET ISLAND",
            "BOTSWANA", "BELARUS", "BELIZE", "CANADA", "COCOS (KEELING) ISLANDS", "CONGO - KINSHASA", "CENTRAL AFRICAN REPUBLIC",
            "CONGO - BRAZZAVILLE", "SWITZERLAND", "CÔTE D’IVOIRE", "COOK ISLANDS", "CHILE", "CAMEROON", "CHINA",
            "COLOMBIA", "COSTA RICA", "CUBA", "CAPE VERDE", "CURAÇAO", "CHRISTMAS ISLAND", "CYPRUS", "CZECH REPUBLIC",
            "GERMANY", "DJIBOUTI", "DENMARK", "DOMINICA", "DOMINICAN REPUBLIC", "ALGERIA", "ECUADOR", "ESTONIA",
            "EGYPT", "WESTERN SAHARA", "ERITREA", "SPAIN", "ETHIOPIA", "FINLAND", "FIJI", "FALKLAND ISLANDS",
            "MICRONESIA", "FAROE ISLANDS", "FRANCE", "GABON", "UNITED KINGDOM", "GRENADA", "GEORGIA", "FRENCH GUIANA",
            "GUERNSEY", "GHANA", "GIBRALTAR", "GREENLAND", "GAMBIA", "GUINEA", "GUADELOUPE", "EQUATORIAL GUINEA",
            "GREECE", "SOUTH GEORGIA & SOUTH SANDWICH ISLANDS", "GUATEMALA", "GUAM", "GUINEA-BISSAU", "GUYANA",
            "HONG KONG SAR CHINA", "HEARD & MCDONALD ISLANDS", "HONDURAS", "CROATIA", "HAITI", "HUNGARY", "INDONESIA",
            "IRELAND", "ISRAEL", "ISLE OF MAN", "INDIA", "BRITISH INDIAN OCEAN TERRITORY", "IRAQ", "IRAN", "ICELAND",
            "ITALY", "JERSEY", "JAMAICA", "JORDAN", "JAPAN", "KENYA", "KYRGYZSTAN", "CAMBODIA", "KIRIBATI", "COMOROS",
            "ST KITTS & NEVIS", "NORTH KOREA", "SOUTH KOREA", "KUWAIT", "CAYMAN ISLANDS", "KAZAKHSTAN", "LAOS", "LEBANON",
            "ST LUCIA", "LIECHTENSTEIN", "SRI LANKA", "LIBERIA", "LESOTHO", "LITHUANIA", "LUXEMBOURG", "LATVIA", "LIBYA",
            "MOROCCO", "MONACO", "MOLDOVA", "MONTENEGRO", "ST MARTIN", "MADAGASCAR", "MARSHALL ISLANDS", "NORTH MACEDONIA",
            "MALI", "MYANMAR (BURMA)", "MONGOLIA", "MACAO SAR CHINA", "NORTHERN MARIANA ISLANDS", "MARTINIQUE", "MAURITANIA",
            "MONTSERRAT", "MALTA", "MAURITIUS", "MALDIVES", "MALAWI", "MEXICO", "MALAYSIA", "MOZAMBIQUE", "NAMIBIA",
            "NEW CALEDONIA", "NIGER", "NORFOLK ISLAND", "NIGERIA", "NICARAGUA", "NETHERLANDS", "NORWAY", "NEPAL", "NAURU",
            "NIUE", "NEW ZEALAND", "OMAN", "PANAMA", "PERU", "FRENCH POLYNESIA", "PAPUA NEW GUINEA", "PHILIPPINES", "PAKISTAN",
            "POLAND", "ST PIERRE & MIQUELON", "PITCAIRN ISLANDS", "PUERTO RICO", "PALESTINIAN TERRITORIES", "PORTUGAL", "PALAU", "PARAGUAY", "QATAR",
            "RÉUNION", "ROMANIA", "SERBIA", "RUSSIA", "RWANDA", "SAUDI ARABIA", "SOLOMON ISLANDS", "SEYCHELLES", "SUDAN",
            "SWEDEN", "SINGAPORE", "ST HELENA", "SLOVENIA", "SVALBARD & JAN MAYEN", "SLOVAKIA", "SIERRA LEONE", "SAN MARINO",
            "SENEGAL", "SOMALIA", "SURINAME", "SOUTH SUDAN", "SÃO TOMÉ & PRÍNCIPE", "EL SALVADOR", "SINT MAARTEN", "SYRIA",
            "ESWATINI", "TURKS & CAICOS ISLANDS", "CHAD", "FRENCH SOUTHERN TERRITORIES", "TOGO", "THAILAND", "TAJIKISTAN",
            "TOKELAU", "TIMOR-LESTE", "TURKMENISTAN", "TUNISIA", "TONGA", "TURKEY", "TRINIDAD & TOBAGO", "TUVALU", "TAIWAN",
            "TANZANIA", "UKRAINE", "UGANDA", "US OUTLYING ISLANDS", "UNITED STATES", "URUGUAY", "UZBEKISTAN", "VATICAN CITY",
            "ST VINCENT & THE GRENADINES", "VENEZUELA", "BRITISH VIRGIN ISLANDS", "US VIRGIN ISLANDS", "VIETNAM", "VANUATU",
            "WALLIS & FUTUNA", "SAMOA", "YEMEN", "MAYOTTE", "SOUTH AFRICA", "ZAMBIA", "ZIMBABWE"
    };

    //compares input against the array of countries
    public boolean isValidCountry (String country){
        return Arrays.asList(countries).contains(country);
    }
}
