package fr.craftinglabs.blink1.command;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PlayPatternCommandTest extends BlinkCommandTest {

    private static final int TYPE_INDEX = 1;
    private static final int REPEAT_INDEX = 5;
    private static final int ENDING_POINT_INDEX = 4;
    private static final int STARTING_POINT_INDEX = 3;

    @Test public void 
    should_have_type_set_to_p() {
        PlayPatternCommand command = new PlayPatternCommand();

        assertThat(command.asBytes()[TYPE_INDEX], is((byte) 'p'));
    }
    
    @Test public void 
    should_have_play_byte_set_to_1() {
        PlayPatternCommand command = new PlayPatternCommand();

        assertThat(command.asBytes()[2], is((byte) 1));
    }
    
    @Test public void 
    should_have_starting_point_set_to_0_by_default() {
        PlayPatternCommand command = new PlayPatternCommand();

        assertThat(command.asBytes()[STARTING_POINT_INDEX], is((byte) 0));
    }

    @Test public void 
    should_have_ending_point_set_to_0_by_default() {
        PlayPatternCommand command = new PlayPatternCommand();

        assertThat(command.asBytes()[ENDING_POINT_INDEX], is((byte) 0));
    }

    @Test public void 
    should_have_number_of_repeat_set_to_0_by_default() {
        PlayPatternCommand command = new PlayPatternCommand();

        assertThat(command.asBytes()[REPEAT_INDEX], is((byte) 0));
    }

    @Test public void 
    should_have_unused_byte_6_7_set_to_0() {
        PlayPatternCommand command = new PlayPatternCommand();

        assertThat(command.asBytes()[6], is((byte) 0));
        assertThat(command.asBytes()[7], is((byte) 0));
    }

    @Test public void 
    should_have_type_set_to_p_when_created_with_all_parameters() {
        PlayPatternCommand command = new PlayPatternCommand(0, 0, 0);

        assertThat(command.asBytes()[TYPE_INDEX], is((byte) 'p'));
    }

    @Test public void 
    should_have_play_byte_set_to_1_when_created_with_all_parameters() {
        PlayPatternCommand command = new PlayPatternCommand(0, 0, 0);

        assertThat(command.asBytes()[2], is((byte) 1));
    }

    @Test public void 
    should_starting_point_correctly_set_when_created_with_all_parameters() {
        PlayPatternCommand command = new PlayPatternCommand(4, 0, 0);

        assertThat(command.asBytes()[STARTING_POINT_INDEX], is((byte) 4));
    }

    @Test public void 
    should_ending_point_correctly_set_when_created_with_all_parameters() {
        PlayPatternCommand command = new PlayPatternCommand(0, 10, 0);

        assertThat(command.asBytes()[ENDING_POINT_INDEX], is((byte) 10));
    }

    @Test public void 
    should_number_of_repeat_correctly_set_when_created_with_all_parameters() {
        PlayPatternCommand command = new PlayPatternCommand(0, 0, 8);

        assertThat(command.asBytes()[REPEAT_INDEX], is((byte) 8));
    }

    @Override
    protected BlinkCommand createCommand() {
        return new PlayPatternCommand();
    }

}
