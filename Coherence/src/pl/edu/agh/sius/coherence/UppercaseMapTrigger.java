package pl.edu.agh.sius.coherence;

import com.tangosol.util.MapTrigger;

public class UppercaseMapTrigger implements MapTrigger {

    private static final long serialVersionUID = 5441785267545525902L;

    @Override
    public void process(Entry entry) {
        Person person = (Person) entry.getValue();
        String surname = person.getSurname();
        person.setSurname(surname.toUpperCase());

        entry.setValue(person);
        System.out.printf("Changed surname to uppercase (%s)\n", 
                person.getSurname());
    }

}
